(ns freq.core
  (:gen-class))

(defn words [line]
  (re-seq #"\w+" line))

(defn allwords [lines]
  (flatten (map words lines)))

(defn countwords [words]
  (frequencies (filter (comp not nil?) words)))

(defn freq [stream]
  (let [counts (countwords (allwords (line-seq stream)))]
    (into (sorted-map-by
      (fn [k1, k2] (<= (counts k2) (counts k1)))) counts)))

(defn -main []
  (doseq [keyval (freq (java.io.BufferedReader. *in*))]
    (println (key keyval) (val keyval))))
