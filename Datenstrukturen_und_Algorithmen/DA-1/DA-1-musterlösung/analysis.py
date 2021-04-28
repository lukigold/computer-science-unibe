#!/usr/bin/env python3
# coding: utf-8

# Read results.csv
import csv
lens = []
dur_builtin = []
dur_merge = []
dur_insert = []
with open("results.csv") as fp:
    reader = csv.reader(fp, delimiter=";")
    next(reader, None) # skip headers
    for row in reader:
        l,i,m,b = tuple(map(int, row))
        lens.append(l)
        dur_builtin.append(b/1000.)
        dur_merge.append(m/1000.)
        if i > -1:
            dur_insert.append(i/1000.)


# Perform linear regression
from scipy import stats
from math import log

n2 = [n**2 for n in lens]
nlogn = [n * log(n) for n in lens]

for (n, rt) in [("merge", dur_merge), ("builtin", dur_builtin)]:
    slope, intercept, r_value, p_value, std_err = stats.linregress(
        nlogn,
        rt)

    print("T_{}: {:.5} * nlogn + {:.5}; R^2 = {:.5}".format(n, slope, intercept,r_value**2))
    

slope, intercept, r_value, p_value, std_err = stats.linregress(
    n2[:len(dur_insert)],
    dur_insert)

print("T_insertion: {:.5} * n^2 + {:.5}; R^2 = {:.5}".format(slope, intercept,r_value**2))
n=1e7
expected_s = n**2*slope+intercept

from datetime import datetime, timedelta
t = timedelta(seconds=expected_s)
print("Expected time for insertion sort of {} items: {}".format(n, t))


import matplotlib.pyplot as plt

plt.figure(figsize=(10,8))
plt.xlabel("input size $n$")
plt.ylabel("runtime [s]")
shape=25
plt.scatter(lens, dur_builtin, s=shape, label="Java builtin")
plt.scatter(lens, dur_merge, s=shape, label="Merge sort")
plt.scatter(lens[:len(dur_insert)], dur_insert, s=shape, label="Insertion sort")
plt.legend()
plt.show()
plt.savefig("runtime.pdf", bbox_inches="tight")





