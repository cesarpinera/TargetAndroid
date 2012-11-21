(defproject factorial "0.1.0-SNAPSHOT"
  :description "A simple factorial library"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/common"]
  :java-source-paths ["src/java"]
  :profiles {:non-android {:dependencies [[org.clojure/clojure "1.4.0"]]
                           :source-paths ["src/non-android"]}
             :android {:dependencies [[android/clojure "1.4.0"]]
                       :source-paths ["src/android"]
                       :aot :all
                       :aot-exclude-ns ["clojure.parallel"]
                       :exclusions [[org.clojure/clojure]]}})
