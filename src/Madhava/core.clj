(ns Madhava.core
  (:require [criterium.core :refer :all]))

(defn custom-types-on []
  (ns Madhava.core_types
    (:require [Madhava.core_types])))

(defn custom-types-off []
  (ns Madhava.core
    (:require [Madhava.core])))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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

(defn coerce-series [s]
  (lazy-cat
   (if (seq? s)
     (reverse s)
     s)
   (repeat 0)))

(defn mul-series [s1 s2]
  (cons (* (first s1)
           (first s2))
        (lazy-seq (add-series (scale-series (rest s2) (first s1))
                              (mul-series (rest s1) s2)))))

(defn invert-series [s]
  (lazy-cat [1]
            (negate-series
             (mul-series (rest s)
                         (invert-series s)))))

(defn div-series [s1 s2]
  (if (zero? (first s2))
    (println "ERROR: denominator has a zero constant term")
    (scale-series (mul-series s1
                              (invert-series (scale-series s2 (/ 1 (first s2)))))
                  (/ 1 (first s2)))))

(defn pow-series [s x]
  (apply (partial map *) (repeat x s)))

(defn compose-series [f g]
  (cons
   (first f)
   (lazy-seq (mul-series (rest g)
                         (compose-series (rest f)
                                         (cons 0
                                               (rest g)))))))

(defn reverse-series [s]
  (lazy-cat [0]
            (invert-series
             (compose-series s
                             (reverse-series s)))))

(defn differentiate-series [s]
  (map * s ints))

(defn integrate-series [s]
  (map / s ints))

(defn sqrt-series [s]
  (lazy-cat [1]
            (add-series (repeat 1)
                        (integrate-series
                         (div-series
                          (differentiate-series s)
                          (scale-series (sqrt-series s) 2))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; SERIES ACCELERATION

(defn euler-transform [s]
  (let [s0 (nth s 0)           
        s1 (nth s 1)          
        s2 (nth s 2)]
    (lazy-seq
     (cons
      (- s2 (/ (* (- s2 s1) (- s2 s1))
               (+ s0 (* -2 s1) s2)))
      (euler-transform (rest s))))))
(defn make-triangle [s]
  (lazy-cat [s]
            (make-triangle
             (euler-transform s))))
(defn accelerate-series [s]
  (map first
       (make-triangle s)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; CONVERGENT SERIES

(defn exp-series []
  (->> (exp-series)
       (integrate-series)
       (lazy-cat [1])))

(defn exp-series' []
  (->> (exp-series')
       (differentiate-series)
       (lazy-cat [1])))

(declare cos-series)
(defn sin-series []
  (->> (cos-series)
       (integrate-series)
       (lazy-cat [0])))

(defn cos-series []
  (->> (sin-series)
       (negate-series)
       (integrate-series)
       (lazy-cat [1])))

(defn atan-series []
  (integrate-series
   (cycle [1 0 -1 0])))

(defn tan-series []
  (reverse-series
   (atan-series)))

(defn tan-series' []
  (div-series
   (sin-series)
   (cos-series)))

(declare cosh-series)
(defn sinh-series []
  (->> (cosh-series)
       (integrate-series)
       (lazy-cat [0])))

(defn cosh-series []
  (->> (sinh-series)
       (integrate-series)
       (lazy-cat [1])))

(defn tanh-series []
  (div-series
   (sinh-series)
   (cosh-series)))

(defn ln-series []
  (integrate-series
   (cycle [1 -1])))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; IMPULSE FUNCTIONS

(defn signum [s]
  (cond
    (pos? (first s))  (cons 1 (lazy-seq (signum (rest s))))
    (neg? (first s))  (cons -1 (lazy-seq (signum (rest s))))
    (zero? (first s)) (cons 0 (lazy-seq (signum (rest s))))))

(defn heaviside-step [s]
  (scale-series
   (add-series (repeat 1)
               (signum s))
   (/ 1 2)))

(defn dirac-delta [s]
  (scale-series
   (differentiate-series
    (signum s))
   (/ 1 2)))

(defn fourier-transform [s]
  (integrate-series
   (mul-series
    (invert-series (exp-series))
    s)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; GENERATING FUNCTIONS

(defn powers [exponent]
  (pow-series ints exponent))

(defn zeta [x]
  (map / (repeat 1) (powers x)))

(defn fib []
  (lazy-cat [0 1]
            (map +
                 (fib)
                 (rest (fib)))))

(defn catalan []
  (cons 1
        (lazy-seq
         (mul-series (catalan)
                     (catalan)))))

(defn partitions []
  (letfn [(p [n]
            (cons 1
                  (lazy-seq
                   (add-series (p (+ n 1))
                               (concat
                                (repeat (- n 1) 0) (p n))))))]
    (cons 1
          (p 1))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; SUMMATIONS

(defn pi [decimals iterations]
  (with-precision decimals
    (* 4M
       (reduce (comp double +)
               (take iterations
                     (atan-series))))))

(defn ln2 [decimals iterations]
  (with-precision decimals
    (* 1M
       (reduce +
               (take iterations
                     (ln-series))))))

(defn basel [decimals iterations]
  (with-precision decimals
    (* 1M
       (reduce +
               (take iterations
                     (zeta 2))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn -main []
  )
