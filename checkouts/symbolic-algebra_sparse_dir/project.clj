(defproject symbolic-algebra "0.1.0"
  :description "symbolic algebra library with numerical tower"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]]
  :main ^:skip-aot symbolic-algebra.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
