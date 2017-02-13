(ns power-series.core
  (:use criterium.core))

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
                   (mul-series (rest s)
                               (invert-series s))))))

(defn div-series [s1 s2]
  (if (zero? (first s2))
    (println "ERROR: denominator has a zero constant term")
    (scale-series (mul-series s1
                              (invert-series (scale-series s2 (/ 1 (first s2)))))
                  (/ 1 (first s2)))))
           
(defn differentiate-series [s]
  (map * s ints))

(defn integrate-series [s]
  (map / s ints))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; TESTS

;; INTEGRALS
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

;; DERIVATIVES
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

(defn tangent-series []
  (div-series
   (sine-series)
   (cosine-series)))

(defn arctan-series []
  "Madhava-Leibniz series"
  (defn arctan [n]
    (lazy-seq
     (cons (/ 1 n)
           (map -
                (arctan (+ n 2))))))
  (arctan 1))

(defn pi [precision]
  (with-precision 1000
    (* 4M
       (reduce +
               (take precision
                     (arctan-series))))))

;; EXPONENTIAL FUNCTION
(defn exp-series []         ;integral
  (cons 1
        (lazy-seq
         (integrate-series
          (exp-series)))))
;; (defn exp-series []      ;derivative
;;   (map / ones
;;        ((fn exp []
;;          (cons 1
;;                (lazy-seq
;;                 (differentiate-series
;;                  (exp))))))))
