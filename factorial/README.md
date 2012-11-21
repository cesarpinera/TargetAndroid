# Sharing Clojure code with an Android application

## Base libraries

- android/clojure 
	- Slimmed down version of Clojure 1.4 for Android
	- https://github.com/sattvik/clojure
	
- android/lein-droid
	- Build environment
	- https://github.com/alexander-yakushev/lein-droid
	- Add to profiles.clj
	
 		{:user
  			{:plugins [[lein-droid "0.1.0-beta1"]]}
  		 :android {:sdk-path "your android sdk path goes here"}}
  		 
	- Follow the Tutorial
	
- neko/neko
	- Clojurized version of some necessary Android classes (e.g. Context, Resource, Threading...)
	- Useful for native applications, not so much for cross-platform code
	
## Steps
	
1. Motivation
	- Solve a problem in Clojure
	- Reuse the Clojure code in an Android application
	- Avoid forking the Clojure code for each target

1. Start with a vanilla Clojure project 
	- No need for lein droid new ...

1. Create a Leiningen profile for each target in project.clj

	; project.clj
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
	
1. Run lein deps for each profile
	- lein with-profile android deps
	- lein with-profile non-android deps
		
1. Write beautiful Clojure code
	- Try to stay in src/common
	- Try to stay away from futures
	- Be kind to the stack

	; src/common/factorial/factorial.clj
	(ns factorial.factorial)
	
	(defn factorial [n]
	  (reduce * (range 1 (inc n))))

1. [optional] Create a Java-friendly wrapper for Clojure code

	;src/android/factorial/FactorialClient.clj
	(ns factorial.FactorialClient
	  (:use [factorial.factorial :only [factorial]])
	  (:gen-class
	   :methods [#^{:static true} [factorial [Long] Long]]))
	
	(defn -factorial
	  [n]
	  (factorial n))
  
1. Try it in the REPL
	- lein with-profile android ritz 
	- lein with-profile android swank
	- jack-in won't work with a profile 
	- ClassNotFoundException java.lang.ClassNotFoundException: clojure.lang.RT <- You didn't select a profile
	
	(require 'factorial.factorial)
	(factorial.factorial/factorial 10)
	
	(require 'factorial.FactorialClient)
	(factorial.FactorialClient/-factorial 10)
	
1. Create your jar file
	- lein with-profile android uberjar

1. Create an Android project
	- Copy the jar to libs
	- Add it to the Build Path (make sure to select the -standalone version)
	- Call it
	- Run the App
	- FOR GREAT GOOD!




	