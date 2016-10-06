(ns power-series.core)

(def ones (repeat 1))
(def ints (drop 1 (range)))

(defn add-series [s1 s2]
  (map + s1 s2))

(defn sub-series [s1 s2]
  (map - s1 s2))

(defn scale-series [s x]
  (let [s2 (repeat x)]
    (map * s s2)))

(defn negate-series [s]
  (scale-series s -1))

(defn mul-series [s1 s2] 
   (cons (* (first s1)
            (first s2))
         (lazy-seq (add-series (scale-series (rest s2) (first s1))
                               (mul-series (rest s1) s2)))))

(defn invert-series [s]
   (cons 1
        (lazy-seq (negate-series
                   (mul-series (next s)
                               (invert-series s))))))

(defn div-series [s1 s2]
  (if (zero? (first s2))
    (println "ERROR: denominator has a zero constant term")
    (scale-series (mul-series s1
                              (invert-series (scale-series s2 (/ 1 (first s2)))))
                  (/ 1 (first s2)))))

(defn compose-series [f g]
  (mul-series
   (cons (first f)
         g)
   (compose-series
    (rest f)
    g)))

;; (cons (cons (first f)
;;             (mul-series 1 (rest f)))
;;       (compose-series g))
           
(defn differentiate-series [s]
  (map * s ints))

(defn integrate-series [s]
  (map / s ints))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; TESTS

;; integrals tests
(declare cosine-series)
(defn sine-series []
  (cons 0
        (lazy-seq
         (integrate-series
          (cosine-series)))))
(defn cosine-series []
  (cons 1
        (lazy-seq
         (integrate-series
          (negate-series
           (sine-series))))))

;; derivatives test
;; (declare cosine-series)
;; (defn sine-series []
;;   (cons 1
;;         (lazy-seq
;;          (differentiate-series
;;           (negate-series
;;            (cosine-series))))))
;; (defn cosine-series []
;;   (cons 0
;;         (lazy-seq
;;          (differentiate-series
;;           (sine-series)))))

(defn -main []
  "test: should be equal to 1"
  (take 1
        (add-series
         (mul-series (sine-series) (sine-series))
         (mul-series (cosine-series) (cosine-series)))))


;; tangent series or Madhava-Leibniz series (1/4 Pi)
(defn tangent-series []
  (div-series (sine-series) (cosine-series)))


;; exponential function
;; (defn exp-series []
;;   (cons 1
;;         (lazy-seq
;;          (integrate-series
;;           (exp-series)))))
(defn exp-series []
  (cons 1
        (lazy-seq
         (differentiate-series
          (exp-series)))))

