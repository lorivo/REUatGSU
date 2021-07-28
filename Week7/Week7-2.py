import pandas as pd
import numpy as np
import pickle as pkl
import matplotlib.pyplot as plt
from scipy import signal
from sklearn.preprocessing import MinMaxScaler
from sklearn.preprocessing import StandardScaler
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn import metrics
from sklearn.pipeline import make_pipeline
from sklearn.svm import SVC
from sklearn import tree
from sklearn.ensemble import RandomForestClassifier
from sklearn.datasets import make_classification

#testing if code works

data = pd.read_csv('finaldata1.csv')
data.columns = ['timestamp','acc_x','acc_y','acc_z','orientation','label']
data.head()

datayt = data.loc[data['label'] == 0]
datascroll = data.loc[data['label'] == 1]
acc_xs = np.array(datascroll['acc_x'])
plt.xlabel('time (sec)')
plt.ylabel("acc_x scroll (m/s^2)")
plt.ylim([-10,35])
plt.plot(acc_xs)
plt.title('x axis scroll application')
plt.show()

acc_xyt = np.array(datayt['acc_x'])
plt.xlabel('time (sec)')
plt.ylabel("acc_x youtube (m/s^2)")
plt.ylim([-10,35])
plt.plot(acc_xyt)
plt.title('x axis youtube application')
plt.show()

acc_ys = np.array(datascroll['acc_y'])
plt.xlabel('time (sec)')
plt.ylabel("acc_y scroll (m/s^2)")
plt.ylim([-10,35])
plt.plot(acc_ys)
plt.title('y axis scroll application')
plt.show()

acc_yyt = np.array(datayt['acc_y'])
plt.xlabel('time (sec)')
plt.ylabel("acc_y youtube (m/s^2)")
plt.ylim([-10,35])
plt.plot(acc_yyt)
plt.title('y axis youtube application')
plt.show()

acc_zs = np.array(datascroll['acc_z'])
plt.xlabel('time (sec)')
plt.ylabel("acc_z scroll (m/s^2)")
plt.ylim([-10,35])
plt.plot(acc_zs)
plt.title('z axis scroll application')
plt.show()

acc_zyt = np.array(datayt['acc_z'])
plt.xlabel('time (sec)')
plt.ylabel("acc_z youtube (m/s^2)")
plt.ylim([-10,35])
plt.plot(acc_zyt)
plt.title('z axis youtube application')
plt.show()

X = np.array(data[['acc_x','acc_y','acc_z','orientation']])
y = np.array(data[['label']])
scaler = StandardScaler()
# transform data
scaled = scaler.fit_transform(X)
#saving the scaler for future use
pkl.dump(scaler,open('scaler.pkl','wb'))
from sklearn.model_selection import KFold
trainscores= []
testscores = []
gnb = GaussianNB()
cv = KFold(n_splits=5, random_state=42, shuffle=False)
for train_index, test_index in cv.split(scaled):
    #print("Train Index: ", train_index, "\n")
    #print("Test Index: ", test_index)

    X_train, X_test, y_train, y_test = scaled[train_index], scaled[test_index], y[train_index], y[test_index]
    gnb.fit(X_train, y_train)
    trainscores.append(gnb.score(X_train,y_train))
    testscores.append(gnb.score(X_test, y_test))
    import statistics

    gnbtrain = statistics.mean(trainscores)
    gnbtest = statistics.mean(testscores)
    print("Mean Training accuracy", gnbtrain)
    print("Mean Testing Accuracy", gnbtest)
    pkl.dump(gnb, open('gnb.pkl', 'wb'))

trainscores= []
testscores = []
svm = SVC(kernel='linear')
cv = KFold(n_splits=5, random_state=42, shuffle=False)
for train_index, test_index in cv.split(scaled):
    #print("Train Index: ", train_index, "\n")
    #print("Test Index: ", test_index)

    X_train, X_test, y_train, y_test = scaled[train_index], scaled[test_index], y[train_index], y[test_index]
    svm.fit(X_train, y_train)
    trainscores.append(svm.score(X_train,y_train))
    testscores.append(svm.score(X_test, y_test))

svmtrain = statistics.mean(trainscores)
svmtest = statistics.mean(testscores)
print("Mean Training accuracy",svmtrain )
print("Mean Testing Accuracy", svmtest )
pkl.dump(svm,open('svm.pkl','wb'))
trainscores= []
testscores = []
destree = tree.DecisionTreeClassifier()
cv = KFold(n_splits=5, random_state=42, shuffle=False)
for train_index, test_index in cv.split(scaled):
    #print("Train Index: ", train_index, "\n")
    #print("Test Index: ", test_index)

    X_train, X_test, y_train, y_test = scaled[train_index], scaled[test_index], y[train_index], y[test_index]
    destree.fit(X_train, y_train)
    trainscores.append(destree.score(X_train,y_train))
    testscores.append(destree.score(X_test, y_test))

destrain = statistics.mean(trainscores)
destest = statistics.mean(testscores)
print("Mean Training accuracy",destrain )
print("Mean Testing Accuracy", destest)
pkl.dump(destree,open('destree.pkl','wb'))


trainscores= []
testscores = []
randtree = RandomForestClassifier(n_estimators=100)
cv = KFold(n_splits=5, random_state=42, shuffle=False)
for train_index, test_index in cv.split(scaled):
    #print("Train Index: ", train_index, "\n")
    #print("Test Index: ", test_index)

    X_train, X_test, y_train, y_test = scaled[train_index], scaled[test_index], y[train_index], y[test_index]
    randtree.fit(X_train, y_train)
    trainscores.append(randtree.score(X_train,y_train))
    testscores.append(randtree.score(X_test, y_test))

rantrain = statistics.mean(trainscores)
rantest = statistics.mean(testscores)
print("Mean Training accuracy",rantrain )
print("Mean Testing Accuracy", rantest )
pkl.dump(randtree,open('randtree.pkl','wb'))
trainacc = [gnbtrain, svmtrain, destrain, rantrain]
testacc = [gnbtest, svmtest, destest, rantest]

x = np.arange(4)
width = 0.2

# plot data in grouped manner of bar type
plt.bar(x, trainacc, width, color='red')
plt.bar(x+0.2, testacc, width, color='green')
plt.xticks(x, ['GNB', 'SVM', 'Descision Tree', 'Random Forest'])
plt.xlabel("ML Models")
plt.ylabel("Accuracies")
plt.rcParams['figure.figsize'] = [20, 4]
plt.legend(["Training acc", "TestAcc"], loc="lower center", bbox_to_anchor=(0.5, -0.3))
plt.show()