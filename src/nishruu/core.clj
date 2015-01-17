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

