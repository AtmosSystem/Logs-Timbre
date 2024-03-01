(defproject org.clojars.atmos-system/atmos-logs-timbre "0.1.0-SNAPSHOT"
  :description "Atmos logs using timbre"
  :url "https://github.com/AtmosSystem/atmos-logs-timbre"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojars.atmos-system/atmos-logs "3.7"]
                 [com.taoensso/timbre "6.3.1"]]

  :repl-options {:init-ns atmos-logs-timbre.core-test}
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.9.0"]]}}

  :deploy-repositories [["clojars" {:sign-releases false
                                    :url           "https://repo.clojars.org/"
                                    :username      :env/clojars_username
                                    :password      :env/clojars_password}]])
