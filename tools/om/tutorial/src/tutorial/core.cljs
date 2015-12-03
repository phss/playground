(ns tutorial.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state  (atom  {:list  ["Lion" "Zebra" "Buffalo" "Antelope"]}))

(defn stripe  [text bgc]
  (let [st #js  {:backgroundColor bgc}]
    (dom/li #js  {:style st} text)))

(om/root
  (fn [data owner]
    (om/component
      (apply dom/ul #js {:className "animals"}
        (map stripe (:list data) (cycle ["#ff0" "#fff"])))))
  app-state
  {:target (. js/document (getElementById "app0"))})


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
