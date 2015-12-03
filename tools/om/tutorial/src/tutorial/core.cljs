(ns tutorial.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state  (atom  {:list  ["Lion" "Zebra" "Buffalo" "Antelope"]}))

(om/root
  (fn [data owner]
    (om/component
      (apply dom/ul #js {:className "animals"}
        (map #(dom/li nil %) (:list data)))))
  app-state
  {:target (. js/document (getElementById "app0"))})


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
