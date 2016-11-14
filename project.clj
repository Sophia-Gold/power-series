(defproject power-series "0.2.0"
  :description "library for computing power series using lazy evaluation"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/core.async "0.2.391"]
                 [symbolic-algebra "0.1.0"]
                 [criterium "0.4.4"]]
  :main ^:skip-aot power-series.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
