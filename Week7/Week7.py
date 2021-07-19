import pandas as pd
import numpy as np
import pickle as pkl
import matplotlib.pyplot as plt
from scipy import signal
from sklearn.preprocessing import MinMaxScaler
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn import metrics
from sklearn.pipeline import make_pipeline
from sklearn.svm import SVC

data = pd.read_csv('finaldata.csv')
data.columns = ['timestamp','acc_x','acc_y','acc_z','orientation','label']
data.head()

datayt = data.loc[data['label'] == 0]
datascroll = data.loc[data['label'] == 1]
acc_xs = np.array(datascroll['acc_x'])
plt.xlabel('time (sec)')
plt.ylabel("acc_x scroll (m/s^2)")
plt.plot(acc_xs)
plt.title('Accelerometer X-Axis Scroll Application')
plt.ylim([-10,35])
plt.show()

acc_xyt = np.array(datayt['acc_x'])
plt.xlabel('time (sec)')
plt.ylabel("acc_x video (m/s^2)")
plt.plot(acc_xyt)
plt.title('Accelerometer X-Axis Video Application')
plt.ylim([-10,35])
plt.show()

acc_ys = np.array(datascroll['acc_y'])
plt.xlabel('time (sec)')
plt.ylabel("acc_y scroll (m/s^2)")
plt.plot(acc_ys)
plt.title('Accelerometer Y-Axis Scroll Application')
plt.ylim([-10,35])
plt.show()

acc_yyt = np.array(datayt['acc_y'])
plt.xlabel('time (sec)')
plt.ylabel("acc_y video (m/s^2)")
plt.plot(acc_yyt)
plt.title('Accelerometer Y-Axis Video Application')
plt.ylim([-10,35])
plt.show()

acc_zs = np.array(datascroll['acc_z'])
plt.xlabel('time (sec)')
plt.ylabel("acc_z scroll (m/s^2)")
plt.plot(acc_zs)
plt.title('Accelerometer Z-Axis Scroll Application')
plt.ylim([-10,35])
plt.show()

acc_zyt = np.array(datayt['acc_z'])
plt.xlabel('time (sec)')
plt.ylabel("acc_z video (m/s^2)")
plt.plot(acc_zyt)
plt.title('Accelerometer Z-Axis Video Application')
plt.ylim([-10,35])
plt.show()

data_acc = data[['acc_x','acc_y','acc_z']]
data_arr = np.array(data_acc)
sos = signal.butter(20, 25, 'lp', fs=1000, output='sos')
filtered = signal.sosfilt(sos, data_arr)

denoised = pd.DataFrame(filtered, columns=['acc_x','acc_y','acc_z'])

denoised['orientation'] = data[['orientation']]
denoised['label'] = data[['label']]
datayt = denoised.loc[denoised['label'] == 0]
datascroll = denoised.loc[denoised['label'] == 1]
acc_xs = np.array(datascroll['acc_x'])
plt.xlabel('time (sec)')
plt.ylabel("acc_x scroll (m/s^2)")
plt.plot(acc_xs)
plt.title('Accelerometer X-Axis Scroll Application')
plt.ylim([-10,35])
plt.show()

acc_xyt = np.array(datayt['acc_x'])
plt.xlabel('time (sec)')
plt.ylabel("acc_x video (m/s^2)")
plt.plot(acc_xyt)
plt.title('Accelerometer X-Axis Video Application')
plt.ylim([-10,35])
plt.show()

acc_ys = np.array(datascroll['acc_y'])
plt.xlabel('time (sec)')
plt.ylabel("acc_y scroll (m/s^2)")
plt.plot(acc_ys)
plt.title('Accelerometer Y-Axis Scroll Application')
plt.ylim([-10,35])
plt.show()

acc_yyt = np.array(datayt['acc_y'])
plt.xlabel('time (sec)')
plt.ylabel("acc_y video (m/s^2)")
plt.plot(acc_yyt)
plt.title('Accelerometer Y-Axis Video Application')
plt.ylim([-10,35])
plt.show()

acc_zs = np.array(datascroll['acc_z'])
plt.xlabel('time (sec)')
plt.ylabel("acc_z scroll (m/s^2)")
plt.plot(acc_zs)
plt.title('Accelerometer Z-Axis Scroll Application')
plt.ylim([-10,35])
plt.show()

acc_zyt = np.array(datayt['acc_z'])
plt.xlabel('time (sec)')
plt.ylabel("acc_z video (m/s^2)")
plt.plot(acc_zyt)
plt.title('Accelerometer Z-Axis Video Application')
plt.ylim([-10,35])
plt.show()

#Saving the denoised data
denoised.to_csv('denoiseddata.csv',index=False)

X = np.array(denoised[['acc_x','acc_y','acc_z','orientation']])
y = np.array(denoised[['label']])
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.4, random_state=23)

scaler = MinMaxScaler()
# transform data
scaled = scaler.fit_transform(X_train)
#saving the scaler for future use
pkl.dump(scaler,open('scaler.pkl','wb'))

gnb = GaussianNB()
gnb.fit(scaled, y_train)
#Saving the naive bayes model
pkl.dump(gnb,open('nb.pkl','wb'))

scaler = pkl.load(open('scaler.pkl', 'rb'))
X_test_normalized = scaler.transform(X_test)

y_pred = gnb.predict(X_test_normalized)
print("Testing Accuracy:",metrics.accuracy_score(y_test, y_pred))

y_predtrain = gnb.predict(scaled)
print("Training Accuracy:",metrics.accuracy_score(y_train, y_predtrain))

svm_clf = SVC(kernel='linear')
svm_clf.fit(scaled, y_train)

scaler = pkl.load(open('scaler.pkl', 'rb'))
X_test_normalized = scaler.transform(X_test)

y_pred = svm_clf.predict(X_test_normalized)
print("Testing Accuracy:",metrics.accuracy_score(y_test, y_pred))

y_predtrain = svm_clf.predict(scaled)
print("Training Accuracy:",metrics.accuracy_score(y_train, y_predtrain))