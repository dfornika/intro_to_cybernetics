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

;; Ex. 6:
;; Arthur and Bill agree to have a gamble. Each is to divide his money into
;; two equal parts, and at the umpire's signal each is to pass one part over
;; to the other player. Each is then again to divide his new wealth into two
;; equal parts and at a signal to pass a half to the other; and so on.
;; Arthur started with 8/- and Bill with 4/-. Represent the initial operand by
;; the vector [8, 4].
;; Find, in any way you can, all its subsequent transforms.
(defn ex-06-transform
  [[a b]]
  [(+ (/ a 2) (/ b 2))
   (+ (/ b 2) (/ a 2))])

(comment
  (ex-06-transform [8 4]) ; => [6 6]
  )

;; Ex. 8:
;; Charles and david decide to play a similar game except that each will
;; hand over a sum equal to a half of what the *other* posesses.
;; If they start with 30/- and 34/- respectively, what will happen to these
;; quantities?
(defn ex-07-transform
  [[c d]]
  [(+ (- c (/ d 2))
      (/ c 2))
   (+ (- d (/ c 2))
      (/ d 2))])

(comment
  (ex-07-transform [30 34]) ; => [28 36]
  (recursive-transform ex-07-transform [30 34] 5) ; => [[30 34] [28 36] [24 40] [16 48] [0 64]]
  )

;; Ex. 10:
;; If, in Ex. 8, other sums of money had been started with, who in general would be the winner?
(comment
  (map ex-07-transform [[28 36]]) 
  )
