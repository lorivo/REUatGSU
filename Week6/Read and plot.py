import pandas as pd #Reading data files
import matplotlib.pyplot as plt #Plotting the sensor values
import numpy as np

datascroll = pd.read_csv('dc1.csv') #read_csv because we have a csv file
datascroll.columns= ['Timestamp','Acc_x','Acc_y','Acc_z','Orientation'] #assigning column values
print(datascroll.head()) #making sure we have read the data

datayt = pd.read_csv('dc2.csv')
datayt.columns= ['Timestamp','Acc_x','Acc_y','Acc_z','Orientation'] #assigning column values
print(datayt.head()) #making sure we have read the data

acc_xs = np.array(datascroll['Acc_x'])

plt.xlabel('Time')
plt.ylabel("Acc_x scroll 1")

plt.plot(acc_xs)

plt.title('X axis scroll 1 application')
plt.show()

acc_xyt = np.array(datayt['Acc_x'])
plt.xlabel('Time')
plt.ylabel("Acc_x scroll 2 application")

plt.plot(acc_xyt)
plt.title('X axis scroll 2 application')
plt.show()

acc_ys = np.array(datascroll['Acc_y'])
plt.xlabel('Time')
plt.ylabel("Acc_y scroll 1")
plt.plot(acc_ys)
plt.title('Y axis scroll 1 application')
plt.show()

acc_yyt = np.array(datayt['Acc_y'])
plt.xlabel('Time')
plt.ylabel("Acc_y scroll 2")
plt.plot(acc_yyt)
plt.title('Y axis scroll 2 application')
plt.show()

acc_zs = np.array(datascroll['Acc_z'])
plt.xlabel('Time')
plt.ylabel("Acc_z scroll")
plt.plot(acc_zs)
plt.title('Z axis scroll 1 application')
plt.show()

acc_zyt = np.array(datayt['Acc_z'])
plt.xlabel('Time')
plt.ylabel("Acc_z scroll 2 application")
plt.plot(acc_zyt)
plt.title('Z axis scroll 2 application')
plt.show()

or_s = np.array(datascroll['Orientation'])
plt.xlabel('Time')
plt.ylabel("Orientation scroll 1")
plt.plot(or_s)
plt.title('Orientation scroll 1 application')
plt.show()

#%%

or_yt = np.array(datayt['Orientation'])
plt.xlabel('Time')
plt.ylabel("Scroll 2 orientation")
plt.plot(or_yt)
plt.title('Orientation scroll 2 application')
plt.show()