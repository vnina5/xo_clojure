(defproject xo_server "0.1.0-SNAPSHOT"
  :description "XO game written in Clojure"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.clojure/tools.trace "0.7.9"]]
  :repl-options {:init-ns xo-server.core}
  :profiles {:dev {:source-paths ["startup"]}}
  :target-path "target/%s")
