(ns intro-to-cybernetics.chapter-04)

;; Suppose a machine (transducer) P is to be joined to another, R such that P affects R, but not the inverse.
;; R has three transformations:
;;
;;   |
;; | v  | a | b | c | d |
;; |----|---|---|---|---|
;; | R1 | c | d | d | b |
;; | R2 | b | a | d | c |
;; | R3 | d | c | d | b |
;; 
;; P has the following transformation on three states i, j, k:
;;
;;   |
;; | v | i | j | k |
;; |---|---|---|---|
;; | P | k | i | i |
;;
;; P and R are joined such that P's state determines which of R's transformations is active:
;;
;; Z: { state of P:     (i j k)
;;      value of alpha: (2 3 2) 
;;
;; Note that the state of the whole machine is simply a vector with two components [x y]
;; where x is one of a, b, c, d and y is one of i, j, k. The whole machine thus has twelve
;; states, and it can be shown that the state (a, i) undergoes the transitions:
;; (a, i) -> (b, k) -> (a, i)

;; (a, j) -> (d, i) ->

(defn transducer-R [input alpha]
  (let [transformations [{:a :c, :b :d, :c :d, :d :b}
                         {:a :b, :b :a, :c :d, :d :c}
                         {:a :d, :b :c, :c :d, :d :b}]]
    ((get transformations (dec alpha)) input)))

(defn transducer-P [input]
  (let [transformations {:i :k, :j :i, :k :i}]
    (transformations input)))

(defn transducer-Z [input]
  (let [transformations {:i 2, :j 3, :k 2}]
    (transformations input)))

(defn transducer-Q [input]
  (let [second-output (transducer-P (second input))
        alpha (transducer-Z second-output)
        first-output (transducer-R (first input) alpha)]
  [first-output second-output]))

(comment
  )
