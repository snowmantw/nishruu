(ns nishruu.core
  (:gen-class)
  (:require [instaparse.core :as insta]))

(def sample "CAATTGCCATGG")
(def dnaSliceInit {:position 0 :dna "" :rest ""})

(defn -main
  "I don't do a whole lot ... yet."
  [must1 must2 & optionals]
  (if (= nil (first optionals))
    (throw (Exception. "NO SUCH FILE"))
    (def fpath (first optionals))
  )
  (print fpath)
  (with-open [rdr (clojure.java.io/reader fpath)]
    (def numstr (read-string (first (line-seq rdr)))))
  (println (+ 99 numstr))
)

(def sample "CAATTGCCATGG")
(def dnaSliceInit {:position 0 :dna "" :rest ""})

; Trick to simplify the syntax:
; (identity foo) --> (do foo)
; although they have different semantic meanings...
(defn dnaSlice
  "do DNA slicing things"
  [acc x]
  (if (< 5 (acc :position))
    (do
      {:position (acc :position)
       :dna (acc :dna)
       :rest (str (acc :rest) x)
    })
    (do
      {:position (+ 1 (acc :position))
       :dna (str (acc :dna) x)
       :rest ""
    })
  )
)

; note: the double (( )) for 'fn' is necessary: (a guess)
; threading macro could handle the form of (-> a foo bar)
; when foo and bar indicate to functions, and would re-arrange
; it as (bar (foo a)) AND apply it. However, if it's
; (-> a foo (fn [x] (do x))), it becomes '(fn (foo a) (do x)),
; since the macro re-arrange the first argument as the second one's
; parameter. After that, when macro try to evaluate the generated
; list, the error of "Parameter declaration foo should be a vector"
; happens. To solve it, simply add an extra () around the anonymous
; function: (-> a foo ((fn [x] (do x)))), so it becomes
; ((fn [x] (do x)) (foo a)), whic is correct, since now the first
; list element in double parentheses couldn't accept any argument,
; so the marco re-arrange nothing for it, and the result is correct.
(defn dnaSliceImperative
  "do DNA slicing things imperatively"
  [acc x]
  (if (< 5 (acc :position))
    (-> acc
      ((fn [acc] (update-in acc [:position] #(do %))))
      ((fn [acc] (update-in acc [:dna] #(do %))))
      ((fn [acc] (update-in acc [:rest] #(str % x))))
    )
    (-> acc
      ((fn [acc] (update-in acc [:position] #(+ 1 %))))
      ((fn [acc] (update-in acc [:dna] #(str % x))))
      ((fn [acc] (assoc-in acc [:rest] "")))
    )
  )
)

