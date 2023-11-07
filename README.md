![image](https://github.com/TanapatButsai/YakiWebsite/assets/98451881/1af5c66d-e503-476d-acc3-d634fe1d8364)![image](https://github.com/TanapatButsai/YakiWebsite/assets/98451881/75fbeb0a-18c1-4fff-9572-219bea3333a0)# Yakiniku Delivery
## รายชื่อสมาชิก<br>
       6410450770 เจษฎากร บุญเลิศ
       6410450940 ณัฐวรรธน์ อ้อนประเสริฐ
       6410451024 ธนภัทร ตันเจริญ
       6410451059 ธนาภัทร บุตรใส 
## คำแนะนำโปรเจค
-ร้านอาหารชื่อร้านสุดยอดยากิอยากจะมีส่งแบบ Delivery ที่ลูกค้าสามารถเข้ามาสั่งอาหารที่เว็บไซด์ ลูกค้าจะต้องสมัครสมาชิกกับทางร้านผ่านเว็บไซด์โดยที่การสั่งแต่ละครั้งจะได้สะสมแต้ม ลูกค้าจะต้องเลือกอาหารให้ครบ150บาทถึงจะสั่งได้ โดยที่ค่าส่งฟรีทุกที่ โดยที่เว็บไซด์นั้นง่ายต่อการใช้งานของลูกค้า มีเมนูแยกเป็นแต่ละหมวด เช่นของสด ของทานเล่น เครื่องดื่มมีระบบแจ้งเตือนสถานะของสินค้าโดยแจ้งผ่านemailที่ลงทะเบียนเข้ามาโดยมีระบบบคูปองส่วนสดที่สามารถใช้ในการลดราคารายการสั่งซื้อได้
## คำแนะนำในการ install
#ต้องมีโปรแกรม Docker และดาวโหลดโปรแกรม mvn จากลิงก์นี้ https://maven.apache.org/

1.กดปุ่ม code สีเขียวแล้วก็ Download as zip
![image](https://github.com/TanapatButsai/YakiWebsite/assets/98451881/ae0b941e-fe0a-4338-a340-0d65e1776087)

2.กดที่ unzip
![image](https://github.com/TanapatButsai/YakiWebsite/assets/98451881/983f9595-a271-4e06-bed0-d62b292649d4)

3.เปิด terminal และพิมคำสั่งดังนี้
cd /xx/xx/YakiWebsite  (เพื่อไปยังโฟลเดอร์ที่ดาวโหลด)
mvn clean package -DskipTests
docker-compose up -d
![image](https://github.com/TanapatButsai/YakiWebsite/assets/98451881/a09004d0-d7c1-487a-bafb-8d914f877109)

4.พิมคำสั่งดังนี้เพื่อปิด
docker-compose down
![image](https://github.com/TanapatButsai/YakiWebsite/assets/98451881/655847a8-ed84-4d08-8270-d099ca4e1d28)


## Persona
![image](https://github.com/TanapatButsai/YakiWebsite/assets/98309698/bacad371-e0f0-4147-a451-3af55db2e1aa)
## UI Flow
![image](https://github.com/TanapatButsai/YakiWebsite/assets/98309698/b26689b5-4723-4677-9c2e-82a61f1eac3c)
## การทดสอบ ใช้ unit testing
       -LoginTest ทดสอบการLoginขึ่้นระบบโดยใช้ Seleniumโดยการหา WebElement สำหรับ input field ในการกรอกข้อมูล username ใน html
       กรอกข้อมูลใน input field ที่ต้องการทดสอบและสำหรับช่่อง input password เมื่อหาแล้วทำการ sendkey ที่ต้องการจะทดสอบเข้าไประบบจะ
       ตรวจสอบการloginให้ว่าสามารถ login ได้หรือไม่
       -SignupTest
       -PurchaseOrderTest ทดสอบการคิดเงินจากOrderโดยการใช้ Cucumberในการสร้างfeatureและเขียน Scenario ต่างๆเพื่อใช้ในการทดสอบ
## Jira
https://nutthawatao.atlassian.net/jira/software/projects/SCRUM/boards/1/timeline?shared=&atlOrigin=eyJpIjoiNTgwZDk4ODA4ZjQxNGM4NzgyZjBiZjdlODUxOGIyNWQiLCJwIjoiaiJ9
