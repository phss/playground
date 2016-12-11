import speech_recognition

recognizer = speech_recognition.Recognizer()

with speech_recognition.Microphone() as source:
  print("adjusting to ambient noise")
  recognizer.adjust_for_ambient_noise(source)

  print("listening...")
  audio = recognizer.listen(source)

try:
  print("recognizing...")
  print(recognizer.recognize_google(audio))
except speech_recognition.UnknownValueError:
  print("Could not understand audio")
except speech_recognition.RequestError as e:
  print("Recog Error; {0}".format(e))


