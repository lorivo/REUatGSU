import pandas as pd #Reading data files
import matplotlib.pyplot as plt #Plotting the sensor values
import numpy as np

datascroll = pd.read_csv('romainvideo1.csv') #read_csv because we have a csv file
datascroll.columns= ['Timestamp','Acc_x','Acc_y','Acc_z','Orientation'] #assigning column values
print(datascroll.head()) #making sure we have read the data

datayt = pd.read_csv('romainvideo2.csv')
datayt.columns= ['Timestamp','Acc_x','Acc_y','Acc_z','Orientation'] #assigning column values
print(datayt.head()) #making sure we have read the data

acc_xs = np.array(datascroll['Acc_x'])

plt.xlabel('Time')
plt.ylabel("Acc_x video 1")

plt.plot(acc_xs)

plt.title('X axis video 1 application')
plt.show()

acc_xyt = np.array(datayt['Acc_x'])
plt.xlabel('Time')
plt.ylabel("Acc_x video 2 application")

plt.plot(acc_xyt)
plt.title('X axis video 2 application')
plt.show()

acc_ys = np.array(datascroll['Acc_y'])
plt.xlabel('Time')
plt.ylabel("Acc_y video 1")
plt.plot(acc_ys)
plt.title('Y axis video 1 application')
plt.show()

acc_yyt = np.array(datayt['Acc_y'])
plt.xlabel('Time')
plt.ylabel("Acc_y video 2")
plt.plot(acc_yyt)
plt.title('Y axis video 2 application')
plt.show()

acc_zs = np.array(datascroll['Acc_z'])
plt.xlabel('Time')
plt.ylabel("Acc_z video 1")
plt.plot(acc_zs)
plt.title('Z axis video 1 application')
plt.show()

acc_zyt = np.array(datayt['Acc_z'])
plt.xlabel('Time')
plt.ylabel("Acc_z video 2 application")
plt.plot(acc_zyt)
plt.title('Z axis video 2 application')
plt.show()

or_s = np.array(datascroll['Orientation'])
plt.xlabel('Time')
plt.ylabel("Orientation video 1")
plt.plot(or_s)
plt.title('Orientation video 1 application')
plt.show()

#%%

or_yt = np.array(datayt['Orientation'])
plt.xlabel('Time')
plt.ylabel("Orientation video 2")
plt.plot(or_yt)
plt.title('Orientation video 2 application')
plt.show()