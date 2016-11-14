# Symbolic-Algebra.clj

A library for performing algebraic operations across multiple numeric types, inspired by the exercise presented in [Structure and Interpretation of Computer Programs](https://mitpress.mit.edu/sicp/) as well as [Richard Zippel’s Weyl package for Common Lisp](http://www.cs.cornell.edu/rz/computer-algebra.html). 

This version makes use of Java's protocols to handle what Philip Wadler dubbed the [The Expression Problem](http://homepages.inf.ed.ac.uk/wadler/papers/expression/expression.txt). The use of functions for type coercion allow for dispatch based on one argument and therefore no sacrifice in speed on the JVM. 

Additionally, the latest version extends the java.lang.Number abstract class as a base type so that generic operations will be dispatched across whatever numerical types Clojure casts to, everything from shorts to doubles to big-ints. In other words, it’s about the closest you’ll get to operator overloading on the JVM.

So far this implements **rationals**, **complex numbers**, and univariate **polynomials**. There are still some bugs with subtyping due to the need to rewrite a handful of math functions to use these generic methods. This means things like rationals of complex numbers and rational polynomials are yet to come.

Additionally, I’ve put off implementing multivariable polynomials. The obvious solution would be to use [Lagrange interpolation](https://en.wikipedia.org/wiki/Lagrange_polynomial), but in addition to implementation complexity and [Runge's phenomenon](https://en.wikipedia.org/wiki/Runge%27s_phenomenon), Clojure is a Lisp: an expressive language, and I think I may have a much more elegant solution in mind involving mapping variables to namespaces.