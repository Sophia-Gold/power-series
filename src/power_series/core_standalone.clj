(ns power-series.core
  (:require [clojure.core.async
             :as async
             :refer [<! <!! >! >!! chan go go-loop]])
  (:use criterium.core))        

(def ones (repeat 1))
(def ints (drop 1 (range)))

(defn add-series [s1 s2]
  (map + s1 s2))

(defn scale-series [s x]
  (let [s2 (repeat x)]
    (map * s s2)))

;maybe get rid of?
(defn negate-series [s]
  (scale-series s -1))

(defn coerce-series [s]
  (lazy-seq
   (concat s
         (repeat 0))))

(defn mul-series [s1 s2] 
   (cons (* (first s1)
              (first s2))
         (lazy-seq (add-series (scale-series (rest s2) (first s1))
                               (mul-series (rest s1) s2)))))

(defn invert-series [s]
  (lazy-seq
   (cons 1
         (lazy-seq (negate-series
                    (mul-series (rest s)
                                (invert-series s)))))))
              
(defn div-series [s1 s2]
  (if (zero? (first s2))
    (println "ERROR: denominator has a zero constant term")
    (scale-series (mul-series s1
                              (invert-series (scale-series s2 (/ 1 (first s2)))))
                  (/ 1 (first s2)))))

(defn compose-series [f g]
  (cons
   (first f)
   (lazy-seq (mul-series (rest g)
                         (compose-series (rest f)
                                         (cons 0
                                               (rest g)))))))

;might be off?
(defn reverse-series [s]
  (lazy-seq
   (cons 0
         (negate-series
          (compose-series s
                          (reverse-series s))))))

(defn differentiate-series [s]
  (map * s ints))

(defn integrate-series [s]
  (map / s ints))
          
(defn sqrt-series [s]
  (lazy-seq
   (cons 1
         (integrate-series
          (negate-series
           (differentiate-series
            (scale-series
             (sqrt-series s) 2)))))))

(defn euler-transform [s]
  (let [s0 (nth s 0)           
        s1 (nth s 1)           
        s2 (nth s 2)]
    (lazy-seq
     (cons (with-precision 1000
             (- s2 (/ (* (- s2 s1) (- s2 s1))
                      (+ s0 (* -2 s1) s2))))
           (euler-transform (rest s))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; TESTS

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

(defn ln2 [precision]
  (defn ln2-loop [n]
    (lazy-seq
     (cons (with-precision 100 (/ 1M n))
           (map -
                (ln2-loop (+ n 1))))))
  (reduce +
          (take precision
                (ln2-loop 1))))

;spigot version
(defn ln2-spigot [n]
  (/ 1
     (* n
        (Math/pow 2 n))))

;; COERCION
;; x^5 + 2x^4 + 3x^2 -2x - 5
;; (take 10 (coerce-series [1 2 0 3 -2 -5]))
;; (1 2 0 3 -2 -5 0 0 0 0)

;; EXPONENTIAL FUNCTION
(defn exp-series []
  (cons 1
        (lazy-seq
         (integrate-series
          (exp-series)))))

;; (defn exp-async []
;;   (let [exp (chan)]
;;     (cons 1
;;           (lazy-seq
;;            (go []
;;                (integrate-series exp))
;;           exp-async))))

;; (defn consume [n ch]
;;   (dorun (repeatedly n #(<!! ch))))

;; (defn print-consume [n ch]
;;   (dorun (print (repeatedly n #(<!! ch)))))

(defn -main []
  )
