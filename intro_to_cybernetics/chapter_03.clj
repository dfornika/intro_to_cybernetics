(ns intro-to-cybernetics.chapter-03)


(defn ex-01-operator
  ([[a b]]
   [b (- a)]))


(defn run-operator
  [op v n]
  (loop [i 0
         x v]
    (if (<= n i)
      x
      (recur (+ i 1)
             (conj x (op (last x)))))))



(defn ex-02-operator
  [[v w x y z]]
  [w v x z y])

(comment
  (run-operator ex-01-operator [[1/2 2]] 3)
  (run-operator ex-02-operator [[2 1 0 2 2]] 1)
  )
