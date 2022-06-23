# projectSpringBoot

Задание:
 Стандартный вариант: полнофункциональное приложение по заданию 
лабораторных на одном из изученных фреймворков. Реализовать 
дополнительную функциональность: авторизацию, многокритериальный 
поиск, загрузку изображений и другое, обсуждается индивидуально.
Тема: Каталог изделий мастеров-камнерезов Колыванского завода. Для 
каждого изделия кроме фотографии и названия имеется список видов камней, 
которые использовались при создании изделия.

Выполнение: 
Добавлена авторизация администратора с помощью инструментов 
фреймворка spring security. Теперь для доступа к рабочему пространству 
администратора необходимо знать пароль. Ввод логина и пароля описан в 
файле login.html

![alt text](https://sun9-74.userapi.com/impf/syYrGKD1U_cIsieFuLr11WDR28qu05WT7CiX_Q/Y6EC8Lwk8vo.jpg?size=681x577&quality=96&sign=b0e7e7d58f96bb8caa1f0b5354a3a7a2&type=album)

Помимо авторизации, также реализована загрузка изображений:
Пример изменения изображения уже существующего изделия:

![alt text](https://sun9-36.userapi.com/impf/O11qY0fxctpviPLXAAFlml473NX-G00FYD8-hA/1LcVGm9AR9g.jpg?size=1469x820&quality=96&sign=ff023cee5e65b966f8ad89952ced29d6&type=album)

![alt text](https://sun9-17.userapi.com/impf/y3nFL9AxvypjXdJhx8HQvq2crPpx3BlG09td7Q/UmLOJN1Y9gg.jpg?size=1462x764&quality=96&sign=2bb00a3ce5851e282ceab3c38c6ef459&type=album)

Рабочее пространство администратора включает в себя список объектов, с 
возможностью добавлять и удалять. А также редактировать на базе отдельного объекта.

![alt text](https://sun4-16.userapi.com/impf/KB8b0oUxmfNLaHy9ooh7vWHQTy--IQPxcezMHg/VzC-Ivx2xnQ.jpg?size=1472x677&quality=96&sign=b1af4321da020b3b9580920b126b6c8c&type=album)

Как видно, в верхней панели есть только каталог для редактирования, кнопка 
выхода и поиск в каталоге.
Если же зайти как гость, то вкладок в панели больше, а также здесь есть 
кнопка входа в администратора, она ссылается на страницу, требующую 
авторизации, поэтому при нажатии сразу переводит на страницу 
авторизации.

![alt text](https://sun4-12.userapi.com/impf/Op7oZVdRS-g0VjBSfCVcT03trsirzdkVZ5dwuw/JiwTP3lpOBE.jpg?size=1477x528&quality=96&sign=d7a92dbd19eeaf06f11eb8c5dd4d701b&type=album)

Каталог обычного пользователя отличается от каталога администратора. Нет 
возможности добавлять новый товар, только посмотреть подробней.

![alt text](https://sun4-17.userapi.com/impf/sSOh1v_R-3ti39nitjKdr2ruj1wknXdJlO1MTA/3jx61tWxJGw.jpg?size=1475x705&quality=96&sign=b5410a0a8137f91a5416e90fe99cad5d&type=album)

![alt text](https://sun9-1.userapi.com/impf/SRrt3y4ECqAaycX1Oph-x1E2ALnd8f7kQGvc7Q/UKwCHPDBh8E.jpg?size=1468x547&quality=96&sign=cbe9b81dd08635b05a133527c11c0945&type=album)


