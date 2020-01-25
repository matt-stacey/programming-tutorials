print("calculations from july to july")

xterra = 10000
xterra_year = 2004
xterra_bought = 2011
xterra_age = xterra_bought - xterra_year

f150 = 28505
f150_year = 2014
f150_bought = 2019
f150_age = f150_bought - f150_year

next_age = min([xterra_age, f150_age]) if min([xterra_age, f150_age]) >= 5 else 5
start_year = f150_bought
save_years = (f150_bought - xterra_bought)
next_bought = start_year + save_years
next_year = next_bought - next_age
print("save for " + str(save_years) + " full years")
print("in july of " + str(next_bought) + ", buy a year " + str(next_year) + " car")
print()

apr = 0
for r in range(100000): # 100th of a percent in apr
    if xterra * ((1 + r/10000.0) ** (save_years)) > f150:
        apr = 1 + round(r/10000.0, 4)
        break
print("apr: " + str(round((apr - 1) * 100, 2)) + "%")

cpi = 0
for r in range(100000): # 100th of a percent in cpi
    if 188.9 * ((1 + r/10000.0) ** (2018 - 2004)) > 251.107: # cpi data from https://inflationdata.com/Inflation/Consumer_Price_Index/HistoricalCPI.aspx?reloaded=true
        cpi = 1 + round(r/10000.0, 4)
        break
print("average cpi increase: " + str(round((cpi - 1) * 100, 2)) + "%")
print()

# change between apr and cpi to see different costs
pct = 1 + round(7.28/100.0, 4) # 7.28% to get $50k
#pct = apr
#pct = cpi

print("annual rate: " + str(round((pct - 1) * 100, 2)) + "%")
next_cost = round(f150 * pct ** (save_years), 2)
print("approx cost: $" + str(next_cost))
print()

print("paycheck savings over " + str(save_years) + " years: $" + str(round(next_cost / (save_years * 26), 2)))
print("total savings: $" + str((save_years * 26) * round(next_cost / (save_years * 26), 2)))
print()


portion = 0
for yr in range(int(save_years)):
    portion = portion + cpi ** yr
annual = next_cost / portion

savings = 0
for yr in range(int(save_years)):
    current_savings = round(annual * (cpi ** yr), 2)
    paycheck_savings = round(current_savings / 26, 2)
    print("from july " + str(start_year + yr) + " to june " + str(start_year + yr + 1) + ", save $" + str(current_savings) + " ($" + str(paycheck_savings) + " per paycheck)")
    savings = savings + paycheck_savings * 26
    
print()
savings = round(savings, 2)
print("total savings in july " + str(next_bought) + ": $" + str(savings))