(ns chatbott.core
  (:gen-class))

(require 'clojure.walk) 
(:require  'clojure.data.json )
(require 'clojure.string );;libraries to work with json file and converting keys 


(def global-var (:wc true))


(def parks 
   {:name "bertramka"
    :wc true,
     :attractions "cultural monument, classical music concerts, social events, W. A. ​​Mozart Museum",
     :biking true,
     :skating false,
     :sports false,
     :playground false,
     :transportation "trams No. 4, 7, 9, 10, 58, 59.",
     :parking true})


(defn wc_convert_bertramka []
  (clojure.walk/stringify-keys  {:wc true}))
(wc_convert_bertramka)

(defn keys->wc[]
  (println (:wc  (clojure.walk/keywordize-keys parks))))



(keys->wc)

(defn print-message [name]
  (println "Nice to meet you" name))

(defn inp []
  (println "Hello! I am Prague parks chat bot. What is your name?")
  (let [name (read-line)]
    (print-message name)));;greeting code

(def resp1 #{"yes" "Y" "ye" "OK" "sure" "ok" "Yes" "Yeah" "y"})
(def resp2 #{"n" "no" "not" "Nope" "Not" "N"  "No" ""});; functions for negetive and positive responce
(def all_activities #{"wc" "attractions" "biking" "skating" "sports" "playground" "transportation" "parking"})

(defn core []
    (println "Are you intersted in park?")
        (let [x (read-line)]
          (cond
            (contains? resp1 x)(println "There will be some qustions about park you may finde usefull")
            (contains? resp2 x )(println "Sorry, i can give information only about parks")
            :else (do(println"Please,answer the question")))))
(defn activity_wc_choose[] 
  (println "Do you want to know about wc of park")
  (let [x (read-line)] 
    (if (contains? resp1 x)
      (if ( parks :wc) 
        (println "Yes,there is some wc in this park")
        (println "No, there is no wc")) 
      (println "Ok, let's move on"))))

(activity_wc_choose)

(defn activity_attractions_choose []
  (println "Do you want to see attractions of park ")
  (let [x (read-line)]
    (cond 
      (contains? resp1 x)(println "cultural monument, classical music concerts, social events, W. A. ​​Mozart Museum")
      (contains? resp2 x)(println "Ok,let's move on "))))

(defn activity_biking_choose []
  (println "Do you want to bike in this park")
  (let [x (read-line)] 
    (if (contains? resp1 x)
      (if (parks :biking)
        (println "Yes,you can bike in this park")
        (println "Sorry,but you can't bike in this park"))
      (println "Ok,let's move on"))))

(defn activity_skate_choose []
  (println "Do you want to know about skating in this park")
  (let [x (read-line)]
    (if (contains? resp1 x)
      (if (parks :skate)
        (println "Yes,you can skate in this park")
        (println "Sorry,but you can't skate in this park"))
      (println "Ok,let's move on"))))



(defn activity_sports_choose []
  (println "Do you want to know about sport activities")
  (let [x (read-line)]
    (if (contains? resp1 x)
      (if (parks :skate)
        (println "Yes,you can do sport")
        (println "Sorry,but you there is no sport activity in this park"))
      (println "Ok,let's move on"))))

(defn activity_playground_choose []
  (println "Do you want to know about playground in this park")
  (let [x (read-line)]
    (if (contains? resp1 x)
      (if (parks :skate)
        (println "Yes,there is some playgrounds in this park")
        (println "Sorry,but there is no playgrounds in this park"))
      (println "Ok,let's move on"))))


(defn activity_transportation_choose []
  (println "Do you want to see trams which goes through this park ")
  (let [x (read-line)]
    (cond 
      (contains? resp1 x)(println "trams No. 4, 7, 9, 10, 58, 59.")
      (contains? resp2 x)(println "Ok,let's move on "))))


(defn activity_parking_choose []
  (println "Do you want to know about parking in this park")
  (let [x (read-line)]
    (if (contains? resp1 x)
      (if (parks :parking)
        (println "Yes,you can park in this park")
        (println "Sorry,but you can't park in this park"))
      (println "Ok,let's move on"))))



(defn parks-list []
  (println "Do you want to have list of park names ?")
  (let [x (read-line) ]
    (cond
      (contains? resp1 x)(keys->names)
      (contains? resp2 x)(println "Ok,let's move on ")
      ) 
  ))

    
    
(defn start []
  (loop[state :start]
  (inp)(parks-list)(core) 
  (activity_wc_choose) 
  (activity_attractions_choose)
  (activity_biking_choose)
  (activity_skate_choose)
  (activity_sports_choose)
  (activity_playground_choose)
  (activity_transportation_choose)
  (activity_parking_choose)
    (println " I guess  our conversation is finished. Do you want to finish our coversation?") 
        (let [x (read-line) ] 
          (cond 
            (contains? resp1 x) (println "Thank you for conversation , bye!")
            (contains? resp2 x) (recur :start)
        )
      )))
(start)