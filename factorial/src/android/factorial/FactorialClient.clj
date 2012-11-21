(ns factorial.FactorialClient
  (:use [factorial.factorial :only [factorial]])
  (:gen-class
   :methods [#^{:static true} [factorial [Long] Long]]))

(defn -factorial
  [n]
  (factorial n))