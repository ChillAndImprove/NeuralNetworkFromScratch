# NeuralNetworkFromScratch
[Neural Network from scratch in Java] Classifying Mnist data

In this repository a neural network was written in Java which classifies MNIST data.

**What does the data consist of?**

> "The MNIST database (Modified National Institute of Standards and Technology database) is a large database of handwritten digits that is commonly used for> training various image processing systems. [..]
> The MNIST database contains 60,000 training images and 10,000 testing images. "

! [alt text] (https://upload.wikimedia.org/wikipedia/commons/2/27/MnistExamples.png)

The following Java code was programmed as a proof-of-concept prototype without much emphasis on documentation (recognizable by the fact that comments and methods were written in German). It served the understanding of the Gradient Descent Algorithm.

**Windows**:
``
javac Neural Network.java
java neural network
``

**Linux**:
``
make
``

This network has an accuracy of ~ 85%. Which is not very good, convolutional Neural Networks have an accucary of ~99%, but since this was about learning gradient descent algorithm, it's fine

