# [Neural Network From Scratch in Java] Classifying Mnist data

In this repository a neural network was written in Java which classifies MNIST data.

**What does the data consist of?**

> "The MNIST database (Modified National Institute of Standards and Technology database) is a large database of handwritten digits that is commonly used for> training various image processing systems. [..]
> The MNIST database contains 60,000 training images and 10,000 testing images. "

![alt text](https://upload.wikimedia.org/wikipedia/commons/2/27/MnistExamples.png)

The following Java code was programmed as a proof-of-concept prototype without much emphasis on documentation (recognizable by the fact that comments and methods were written in German). It served the understanding of the Gradient Descent Algorithm.

**Windows**:
<br>
``
javac NeuronalesNetz.java
``
<br>
``
java NeuronalesNetz
``

**Linux**:<br>
``
make
``

This network has an accuracy of ~ **85%**. 

Which is not very good, convolutional Neural Networks have an accucary of ~99%, but since this was about learning gradient descent algorithm, it's fine

In order to make this code work MNSIT Dataset is necessary. I've uploaded the files here:

https://www.file-upload.net/download-14510874/mnist_test.txt.html<br>
https://www.file-upload.net/download-14510879/mnist_train.txt.html<br>
just move them into the source code folder.
