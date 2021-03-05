# NeuralNetworkFromScratch
[Neural Network from scratch in Java] Classifying Mnist data 

In diesem Repository wurde ein Neuronale Netz in Java geschrieben was MNIST Daten klassifiziert.

**Woraus bestehen die Daten?**

>"The MNIST database (Modified National Institute of Standards and Technology database) is a large database of handwritten digits that is commonly used for >training various image processing systems.[..]
>The MNIST database contains 60,000 training images and 10,000 testing images."

![alt text](https://upload.wikimedia.org/wikipedia/commons/2/27/MnistExamples.png)

Der folgende Javacode wurde als Proof-Of-Concept Prototyp programmiert ohne viel Wert auf Dokumentation gelegt( erkennbar dadurch das Kommentare und Methoden auf Deutsch geschrieben wurden). Er diente dem Verständis des Gradient Descent Algorithmen. 

**Windows**:
```
javac NeuronalesNetz.java 
java NeuronalesNetz 
```

**Linux**:
```
make 
```

Dieses Netzt schafft eine Genauigkeit von ~85%. Was nicht sehr gut ist. Convolutional Neural Networks schaffen 99%, aber da es hier darum ging das lernen zu verstehen ist es in Ordnung
