(ns factorial.factorial)

(defn factorial [n]
  (reduce * (range 1 (inc n))))
