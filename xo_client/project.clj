(defproject xo_client "0.1.0-SNAPSHOT"
  :description "Custom XO game write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [xo_server "0.1.0-SNAPSHOT"]]
                 ;[clojure-tic-tac-toe "0.1.0-SNAPSHOT"]]
  ;:repl-options {:init-ns xo-client.core}
  :profiles {:dev {:source-paths ["startup"]}
             :uberjar {:aot :all}}
  :target-path "target/%s"
  :main ^:skip-aot xo-client.core)
