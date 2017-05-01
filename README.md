# Madhava

>”Multiply the arc by the square of the arc, and take the result of repeating that (any number of times). Divide (each of the above numerators) by the squares of the successive even numbers increased by that number and multiplied by the square of the radius. Place the arc and the successive results so obtained one below the other, and subtract each from the one above. These together give the jiva, as collected together in the verse beginning with "vidvan" etc."

-[Madhava of Sangamagrama](https://en.wikipedia.org/wiki/Madhava_of_Sangamagrama) (c. 1350 – c. 1425), founder of the Kerala school of astronomy and mathematics

---

## History

By all accounts, computing power series with lazy evaluation was fist mentioned in [an unpublished paper](https://docs.google.com/viewer?url=http%3A%2F%2Fpdos.csail.mit.edu%2F~rsc%2Fkahn77parallel.pdf) on coroutines by Giles Kahn and David MacQueen in 1977, even though it primarily examined the Sieve of Eratosthenes method for finding primes and only mentioned having developed a power series application in the conclusion along with one for Fourier transforms and various sorting algorithms. Regardless, the power series and primes sieve examples quickly spread throughout the functional programming world as canonical numerical demonstrations of [Peter Landin's concept of streams](http://fi.ort.edu.uy/innovaportal/file/20124/1/22-landin_correspondence-between-algol-60-and-churchs-lambda-notation.pdf).

The technique perhaps became most well known through its Scheme implementations included in [SICP](https://mitpress.mit.edu/sicp/), although by now versions exist in numerous languages—even those without support for laziness such as Go.

In 1989 Doug McIlroy wrote a concurrent version using one of Go's [CSP](https://docs.google.com/viewer?url=http%3A%2F%2Fwww.usingcsp.com%2Fcspbook.pdf) predecessors, Newsqueak, and published ["Squinting at Power Series."](https://swtch.com/~rsc/thread/squint.pdf) McIlroy referred to [the subsequent version in Haskell](http://www.cs.dartmouth.edu/~doug/powser.html) as, "the most beautiful code I've ever written," and went on to publish two more papers: ["Functional Pearls: Power Series, Power Serious"](http://www.cs.dartmouth.edu/~doug/pearl.ps.gz) and ["The Music of Streams."](http://www.cs.dartmouth.edu/~doug/music.ps.gz)

The technique was developed significantly in Haskell by Jerzy Karczmarczuk, who wrote [several](http://www.sciencedirect.com/science/article/pii/S0304397597000650) [papers](https://pdfs.semanticscholar.org/4edf/d071cf5012aaa69449c9fe76646955a8d185.pdf) [on the matter](http://www.nt.ntnu.no/users/haugwarb/Programming/Haskell/haskell_automatic_differentiation_II.pdf), and later by [Conal Elliot](http://conal.net/papers/beautiful-differentiation/). Additional sources include [Pavlovic & Escardo](http://www.isg.rhul.ac.uk/dusko/papers/1998-lapl-LICS.pdf), who studied categorical similarities between the operational calculus and list processing, as well as [Jeffrey Siskind & Barak Pearlmutter](http://www.bcl.hamilton.ie/~qobi/nesting/) who wrote several papers on the problem of generating higher order partial derivatives in both Scheme and Haskell.

---

## Custom Typing

Madhava can be used in conjunction with my own [Symbolic Algebra library](https://github.com/Sophia-Gold/Symbolic-Algebra.clj) to add custom numeric types for rational and complex numbers as well as polynomials. To use this version first clone both libraries and run ```lein install``` in the symbolic-algebra directory as well as ```lein deps``` in this one. Symbolic types can then be toggled on and off with ```(custom-types-on)``` and ```(custom-types-off)```. Note some functions (listed below) cannot be used with symbolic types.

---

## Current functions include:

Operations:

+ Addition
+ Subtraction
+ Scaling
+ Negation
+ Coercion (finite to infinite series)
+ Multiplication (Cauchy product)
+ Division
+ Functional Inversion
+ Functional Composition (Horner scheme)
+ Functional Reversion
+ Differentiation
+ Integration

In untyped version only:

+ Exponentiation
+ Square Root
+ Euler Transform (series acceleration)
+ Signum
+ Heaviside Step Function
+ Dirac Delta
+ Fourier Transform
+ Monte Carlo functions

Taylor Series:

+ Exponential Function
+ Sine
+ Cosine
+ Tangent
+ Arctangent
+ Hyperbolic Sine
+ Hyperbolic Cosine
+ Hyperbolic Tangent
+ Natural Logarithm

Generating Functions:

+ Exponentiation
+ Riemann Zeta
+ Fibonacci Series
+ Catalan numbers
+ Partition Function
+ Rowland's Prime Generator
+ Dirichlet's theorem

Decimal Approximations (untyped only):

+ Pi
+ Ln(2)
+ Basel problem