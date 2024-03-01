(ns atmos-logs-timbre.core-test
  (:require [atmos-logs-timbre.core :refer :all]
            [clojure.test :refer :all]))

(require '[atmos-logs.core :as core-log]
         '[clojure.spec.alpha :as s]
         '[clojure.spec.gen.alpha :as gen]
         '[clojure.spec.test.alpha :as s-test])