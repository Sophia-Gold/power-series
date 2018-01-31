(defproject power-series "0.6.0"
  :description "stream processing library for formal power series and generating functions"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"] 
                 [symbolic-algebra "0.5.1"]] 
  :profiles {:dev {:dependencies [[org.clojure/tools.nrepl "0.2.12"]
                                  [criterium "0.4.4"]]}
             :uberjar {:aot :all}}
  :main ^:skip-aot power-series.core
  :target-path "target/%s")
