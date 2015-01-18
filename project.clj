(defproject nishruu "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "GPLv3"
            :url "https://gnu.org/licenses/gpl-3.0.txt"}
  :dependencies [[org.clojure/clojure "1.7.0-alpha4"]
                 [instaparse "1.3.5"]
                 [org.clojure/tools.trace "0.7.8"]]
  :main ^:skip-aot nishruu.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[jonase/eastwood "0.2.1"]]}
            }
)
