COSINE

power-series.core=> (bench (take 10 (cosine-series)))
Evaluation count : 1229471220 in 60 samples of 20491187 calls.
             Execution time mean : 38.129539 ns
    Execution time std-deviation : 0.847677 ns
   Execution time lower quantile : 36.923621 ns ( 2.5%)
   Execution time upper quantile : 39.963326 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 10.9495 % Variance is moderately inflated by outliers

power-series.core=> (bench (take 1000 (cosine-series)))
Evaluation count : 1199291040 in 60 samples of 19988184 calls.
             Execution time mean : 37.754718 ns
    Execution time std-deviation : 0.624736 ns
   Execution time lower quantile : 36.809203 ns ( 2.5%)
   Execution time upper quantile : 39.213695 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 6.2440 % Variance is slightly inflated by outliers

power-series.core=> (bench (take 1000000 (cosine-series)))
Evaluation count : 1224087720 in 60 samples of 20401462 calls.
             Execution time mean : 37.753892 ns
    Execution time std-deviation : 0.637802 ns
   Execution time lower quantile : 36.765845 ns ( 2.5%)
   Execution time upper quantile : 39.080781 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 6.2611 % Variance is slightly inflated by outliers
 

EXPONENTIAL FUNCTION

power-series.core=> (bench (take 10 (exp-series)))
Evaluation count : 1141149840 in 60 samples of 19019164 calls.
             Execution time mean : 37.999353 ns
    Execution time std-deviation : 1.425458 ns
   Execution time lower quantile : 36.646313 ns ( 2.5%)
   Execution time upper quantile : 41.900386 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 5 outliers in 60 samples (8.3333 %)
	low-severe	 1 (1.6667 %)
	low-mild	 4 (6.6667 %)
 Variance from outliers : 23.8434 % Variance is moderately inflated by outliers

power-series.core=> (bench (take 1000 (exp-series)))
Evaluation count : 1170317040 in 60 samples of 19505284 calls.
             Execution time mean : 38.767106 ns
    Execution time std-deviation : 1.018672 ns
   Execution time lower quantile : 37.272561 ns ( 2.5%)
   Execution time upper quantile : 40.508979 ns (97.5%)
                   Overhead used : 12.138640 ns

power-series.core=> (bench (take 1000000 (exp-series)))
Evaluation count : 1173563100 in 60 samples of 19559385 calls.
             Execution time mean : 38.132247 ns
    Execution time std-deviation : 0.535777 ns
   Execution time lower quantile : 37.150929 ns ( 2.5%)
   Execution time upper quantile : 38.991968 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers


INVERSE TANGENT

power-series.core=> (bench (take 10 (arctan-series)))
Evaluation count : 157278060 in 60 samples of 2621301 calls.
             Execution time mean : 369.161685 ns
    Execution time std-deviation : 7.225703 ns
   Execution time lower quantile : 357.973461 ns ( 2.5%)
   Execution time upper quantile : 391.062758 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 7 outliers in 60 samples (11.6667 %)
	low-severe	 2 (3.3333 %)
	low-mild	 2 (3.3333 %)
	high-mild	 3 (5.0000 %)
 Variance from outliers : 7.8613 % Variance is slightly inflated by outliers

power-series.core=> (bench (take 1000 (arctan-series)))
Evaluation count : 158876940 in 60 samples of 2647949 calls.
             Execution time mean : 366.033271 ns
    Execution time std-deviation : 3.368937 ns
   Execution time lower quantile : 359.969902 ns ( 2.5%)
   Execution time upper quantile : 373.653943 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers

power-series.core=> (bench (take 1000000 (arctan-series)))
Evaluation count : 159342120 in 60 samples of 2655702 calls.
             Execution time mean : 365.525396 ns
    Execution time std-deviation : 5.693179 ns
   Execution time lower quantile : 358.637538 ns ( 2.5%)
   Execution time upper quantile : 374.489371 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 1.6389 % Variance is slightly inflated by outliers


TANGENT

power-series.core=> (bench (take 10 (tangent-series)))
Evaluation count : 104703600 in 60 samples of 1745060 calls.
             Execution time mean : 555.516233 ns
    Execution time std-deviation : 6.835787 ns
   Execution time lower quantile : 543.725855 ns ( 2.5%)
   Execution time upper quantile : 568.137138 ns (97.5%)
                   Overhead used : 12.138640 ns
		   
power-series.core=>  (bench (take 1000 (tangent-series)))
Evaluation count : 101923200 in 60 samples of 1698720 calls.
             Execution time mean : 559.851539 ns
    Execution time std-deviation : 10.135665 ns
   Execution time lower quantile : 546.166985 ns ( 2.5%)
   Execution time upper quantile : 578.823819 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 1 outliers in 60 samples (1.6667 %)
	low-severe	 1 (1.6667 %)
 Variance from outliers : 7.7816 % Variance is slightly inflated by outliers

power-series.core=> (bench (take 1000000 (tangent-series)))
Evaluation count : 107892600 in 60 samples of 1798210 calls.
             Execution time mean : 557.287150 ns
    Execution time std-deviation : 14.066453 ns
   Execution time lower quantile : 545.978200 ns ( 2.5%)
   Execution time upper quantile : 577.451131 ns (97.5%)
                   Overhead used : 12.138640 ns

Found 2 outliers in 60 samples (3.3333 %)
	low-severe	 2 (3.3333 %)
 Variance from outliers : 12.6067 % Variance is moderately inflated by outliers


PI

power-series.core=> (bench (pi 10))
Evaluation count : 754320 in 60 samples of 12572 calls.
             Execution time mean : 80.378109 µs
    Execution time std-deviation : 841.989604 ns
   Execution time lower quantile : 78.937647 µs ( 2.5%)
   Execution time upper quantile : 81.798949 µs (97.5%)
                   Overhead used : 12.138640 ns

power-series.core=> (bench (pi 100))
Evaluation count : 26880 in 60 samples of 448 calls.
             Execution time mean : 2.269626 ms
    Execution time std-deviation : 28.056995 µs
   Execution time lower quantile : 2.221772 ms ( 2.5%)
   Execution time upper quantile : 2.317215 ms (97.5%)
                   Overhead used : 12.138640 ns

power-series.core=> (bench (pi 1000))
Evaluation count : 180 in 60 samples of 3 calls.
             Execution time mean : 425.759165 ms
    Execution time std-deviation : 5.262367 ms
   Execution time lower quantile : 417.379364 ms ( 2.5%)
   Execution time upper quantile : 435.425834 ms (97.5%)
                   Overhead used : 12.138640 ns
