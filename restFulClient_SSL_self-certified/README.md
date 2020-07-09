# <b>RESTFul-Client-SSLSelfSigned<b>
<pre>
Creating Self Signed Certificate
Step 1.
D:\>keytool -keypass secret -storepass secret -genkey -alias httpskey -keyalg RSA -keystore D:\https_keystore.jks
What is your first and last name?
  [Unknown]:  Nagaraju Gajula
What is the name of your organizational unit?
  [Unknown]:  RR
What is the name of your organization?
  [Unknown]:  Yas
What is the name of your City or Locality?
  [Unknown]:  Ban
What is the name of your State or Province?
 [Unknown]:  91
What is the two-letter country code for this unit?
  [Unknown]:  91
Is CN=Nagaraju Gajula, OU=RR, O=Yas, L=Ban, ST=91, C=91 correct?
  [no]:  yes


D:\>keytool -export -alias httpskey -keystore D:\https_keystore.jks -storepass secret -file server.cert
Certificate stored in file <server.cert>

D:\>keytool -import -v -trustcacerts -alias httpskey -keystore D:\client_truststore.jks -storepass secret -file server.cert
Owner: CN=Nagaraju Gajula, OU=RR, O=Yas, L=Ban, ST=91, C=91
Issuer: CN=Nagaraju Gajula, OU=RR, O=Yas, L=Ban, ST=91, C=91
Serial number: 4b3167d6
Valid from: Wed Dec 27 16:17:57 IST 2017 until: Tue Mar 27 16:17:57 IST 2018
Certificate fingerprints:
         MD5:  A4:64:C2:00:6D:04:48:21:C4:5D:02:78:EC:F5:E0:E5
         SHA1: AB:48:BE:2D:87:71:03:08:3C:09:FA:AE:B6:0D:0D:4A:D7:E0:60:FE
         SHA256: 00:EE:1C:71:36:1A:C7:69:24:C2:BC:54:E2:96:BD:FF:7C:87:02:87:8B:F4:72:97:11:39:C9:1B:D9:2E:50:29
         Signature algorithm name: SHA256withRSA
         Version: 3

Extensions:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 0E 9A FF 86 9D 6B 34 60   82 AB B7 5E 35 90 71 0E  .....k4`...^5.q.
0010: 13 D3 FD 2D                                        ...-
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore
[Storing D:\client_truststore.jks]

Step 2.
Modification in tomcat/server.xml file..
  Activate the following section and add the keystore file. 
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol" SSLEnabled="true"
              maxThreads="150" scheme="https" secure="true"
              clientAuth="false" sslProtocol="TLS"
	       keystoreFile="D:\https_keystore.jks"
	       keystorePass="secret" />

Step 3.
Code to Test ….
</pre>
