(ns atmos-logs-timbre.core
  (:require
    [atmos-logs.core :as log-core]
    [atmos-logs.spec :as log-spec]
    [clojure.spec.alpha :as s]
    [taoensso.timbre :as timbre])
  (:import (java.util Map)))


(extend-type Map
  log-core/PLogger
  (logger-name [logger] (:name logger))
  (logger-ns [logger] (:ns logger))

  (log-> [logger log-type log-data]
    {:pre  [(log-core/logger-valid? logger)
            (s/valid? ::log-core/log-type log-type)
            (log-spec/log-data-or-exception-valid? log-data)]

     :post [(s/valid? boolean? %)]}

    (when-let [data {:logger logger :event log-data}]
      (case log-type
        :info (timbre/info data)
        :trace (timbre/trace data)
        :warn (timbre/warn data)
        :debug (timbre/debug data)
        :error (timbre/error data)
        :exception (timbre/error data)
        :fatal (timbre/fatal data))

      true))

  log-core/PLoggerActions
  (info [logger data]
    {:pre [(log-spec/log-data-valid? data)]}
    (log-core/log-> logger :info data))

  (trace [logger data]
    {:pre [(log-spec/log-data-valid? data)]}
    (log-core/log-> logger :trace data))

  (warn [logger data]
    {:pre [(log-spec/log-data-valid? data)]}
    (log-core/log-> logger :warn data))

  (debug [logger data]
    {:pre [(log-spec/log-data-valid? data)]}
    (log-core/log-> logger :debug data))

  (error [logger data]
    {:pre [(log-spec/log-data-valid? data)]}
    (log-core/log-> logger :error data))

  (exception [logger ex]
    {:pre [(s/valid? ::log-spec/exception ex)]}
    (log-core/log-> logger :exception ex))

  (fatal [logger data]
    {:pre [(log-spec/log-data-valid? data)]}
    (log-core/log-> logger :fatal data)))

(defn init-logging
  [config]
  (timbre/merge-config! config))
