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
  (map #(recursive-transform ex-07-transform % 4) [[28 36] [26 38]])
  )

;; Ex. 11:
;; In an aquarium two species of animacule are prey and predator.
;; In each day, each predator destroys one prey, and also divides to become two predators.
;; If today the aquarium has m prey and n predators, express their changes as a transform
(defn ex-11-transform
  [[m n]]
  [(- m n)
   (* 2 n)])

(comment
  (recursive-transform ex-11-transform [150 10] 4)
  ) 

(defn ex-14-transform
  [[x y]]
  [(* (- x y) 1/2)
   (* (+ x y) 1/2)])

(comment
  (recursive-transform ex-14-transform [10 10] 8))
  

;; Ex. 16:
;; In a certain economic system a new law enacts that at each yearly
;; readjustment the wages shall be raised by as many shillings as the price index
;; exceeds 100 in points. The economic effect of wages on the price index is such
;; that at the end of any year the price index has become equal to the wage rate
;; at the beginning of the year. Express the changes of wage-level and price-index
;; over the year as a transformation.
(defn ex-16-transform
  [[wages price-index]]
  [(+ wages (- price-index 100))
   wages])

(comment
  (recursive-transform ex-16-transform [110 110] 10))
  

;; Ex. 19
;; The system described above so that the transformation becomes
;; wages' = 1/2(wages + price-index), price-index' = 1/2(wages - price-index) + 100
(defn ex-16-transform
  [[wages price-index]]
  [(* (+ wages price-index) 1/2)
   (+ (* (- wages price-index) 1/2) 100)])

(comment
  (recursive-transform ex-16-transform [110 110] 10)
  (recursive-transform ex-16-transform [80 120] 10)
  )

