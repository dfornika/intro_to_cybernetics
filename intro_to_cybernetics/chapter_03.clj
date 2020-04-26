(ns intro-to-cybernetics.chapter-03)

(defn recursive-transform
  [transform initial-state n]
  (loop [states [initial-state]]
    (if (>= (count states) (+ n 1))
      states
      (recur (conj states (transform (last states)))))))

;; Ex. 1:
;; If the operands are of the form [a, b] and one of them
;; is [1/2, 2], find the vectors produced by repeated application
;; of the transformation T:
;; T:{ a' = b, b' = -a
(defn ex-01-transform
  ([[a b]]
   [b (- a)]))

(comment
  (recursive-transform ex-01-transform [1/2 2] 3)
  )

;; Ex. 2:
;; If the operands are vectors of the form [v, w, x, y, z] and U is:
;; U: { v' = w, w' = v, x' = x, y' = z, z' = y
;; then find U(a) where a = [2, 1, 0, 2, 2]
(defn ex-02-transform
  [[v w x y z]]
  [w v x z y])

(comment
  (recursive-transform ex-02-transform [2 1 0 2 2] 1)
  )

;; Ex. 4:
;; Draw a kinematic graph of U if its only operands are a, U(a), U^2(a), etc.
;; 
;; [2 1 0 2 2] <-> [1 2 0 2 2]

;; Ex. 5:
;; Find the transform of [3, -2, 1] by A if the general form is [g, h, j]
;; and the transformation is:
;; A:{ g' = (2g - h), h' = (h - j), j' = (g + h)
(defn ex-05-transform
  [[g h j]]
  [(- (* 2 g) h)
   (- h j)
   (+ g h)])

(comment
  (ex-05-transform [3 -2 1]) ; => [8 -3 1]
  )
