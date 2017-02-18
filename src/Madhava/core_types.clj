(ns Madhava.core_types
  (:require [symbolic-algebra.core :as sym]
            [criterium.core :refer :all]))

(defn custom-types-on []
  (ns Madhava.core_types
    (:require [Madhava.core_types])))

(defn custom-types-off []
  (ns Madhava.core
    (:require [Madhava.core])))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def ints (map
           #(sym/->Rational % 1)
           (drop 1 (range))))

(defn add-series [s1 s2]
  (map sym/add s1 s2))

(defn sub-series [s1 s2]
  (map sym/sub s1 s2))

(defn scale-series [s x]
  (let [s2 (repeat x)]
    (map sym/mul s s2)))
(defn negate-series [s]
  (scale-series s (sym/->Rational -1 1)))

(defn coerce-series [s]
  (lazy-cat
   (if (seq? s)
     (reverse s)
     s)
   (repeat 0)))

(defn mul-series [s1 s2] 
   (cons (sym/mul (first s1)
                  (first s2))
         (lazy-seq (add-series (scale-series (rest s2) (first s1))
                               (mul-series (rest s1) s2)))))

(defn invert-series [s]
  (lazy-cat [1]
            (negate-series
             (mul-series (rest s)
                         (invert-series s)))))

(defn div-series [s1 s2]
  (if (sym/equal? (first s2) 0)
    (println "ERROR: denominator has a zero constant term")
    (scale-series (mul-series s1
                              (invert-series (scale-series s2 (sym/div 1 (first s2)))))
                  (sym/div 1 (first s2)))))

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
  (map sym/mul s ints))

(defn integrate-series [s]
  (map sym/div s ints))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

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

(declare cosh-series)
(defn sinh-series []
  (->> (cosh-series)
       (integrate-series)
       (lazy-cat [0])))

(defn cosh-series []
  (->> (sinh-series)
       (integrate-series)
       (lazy-cat [1])))

(defn ln-series []
  (integrate-series
   (cycle [1 -1])))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; GENERATING FUNCTIONS

(defn powers [exponent]
  (lazy-cat [1]
            (scale-series
             (powers exponent)
             exponent)))
            
(defn zeta [x]
  (map sym/div (repeat 1) (powers x)))

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

(defn -main []
  )
