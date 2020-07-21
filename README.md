# Theory of Machines and Languages First Project (Q1)

ابتدا چهار کلاس زیر را تعریف میکنیم:

> State

در این کلاس هر حالت را تعریف میکنیم. این کلاس فیلد هایی نظیر عنوان و دو فیلد از نوع بولین دارد که مشخص میکنند که آیا این  حالت یک حالت نهایی/ابتدایی هست یا خیر.

> Path

در این کلاس تلاش بر این داریم که یک مسیر را نمایش بدهیم. یک مسیر یعنی اینکه ما با گرفتن یک ورودی -که میان الفباهای تعریف شده است- از حالت فعلی به حالت بعدی برویم. در اینجا در فیلد اول و سوم به جای اینکه خود حالات را بعنوان فیلد انتخاب کنیم، عنوانشان را برگزیدیم (با این فرض که هیچ دو حالتی وجود ندارند که نامشان یکسان باشد!)

> Acceptor

یک پذیرنده در اصل همان ماشینی است که قصد طراحی آن را داریم. در واقع تمام کلاس های قبل را تعریف کردیم که بتوانیم این کلاس را طراحی کنیم. تمامی حالات، مسیر ها و الفباهای مجاز در این ماشین را داخل این کلاس قرار میدهیم.هنگام ساخته شدن این ماشین دو حالت وجود خواهد داشت(که از کاربر پرسیده میشود):

- ساخته شدن این ماشین را به عهده خود برنامه قرار دهیم. یعنی کاربر هیچ نقشی در ساخته شدن آن نخواهد داشت. این اطلاعات پیش‌فرض، همان مقادیری هستند که در صورت سوال به ما داده شده است.

-	گرفتن اطلاعات از کاربر؛ یعنی کاربر در سطر اول الفباها، سطری بعد تمام حالات موجود در ماشین، سطر بعد حالت شروع سطر بعد حالت های نهایی و در خطوط بعدی را به اطلاعات هریک از مسیر های ماشین اختصاص دهد؛ بدین صورت که در هر سطر به ترتیب عنوان حالت شروع، الفبایی که موجب تغییر حالت میشود و در آخر عنوان حالت پایان را وارد میکند و میان هرکدام از این موارد از space استفاده کند. ورودی گرفتن تا زمانی صورت میگیرد که تعداد قسمت هایی که در هر سطر وارد شده و به وسیله ی space از هم جدا شده اند دقیقاً ۳ باشد. نه کمتر، نه بیشتر...

-	ساخته شدن ماشین از داخل یک فایل متنی؛ برنامه دو گزینه پیش روی ما قرار خواهد داد: ۱) خواندن یک فایل داخل پوشه‌ی تمرین مورد نظر به نام خواسته شده و ۲) خواندن فایل با یک نام که کاربر مشخص میکند.  حال اگر ورودی های داخل فایل مورد نظر صحیح بود ماشین به درستی کار میکند در غیر این صورت برنامه به ما خطا میدهد.

> Main

این کلاس در اصل برای ارتباط برنامه با کاربر طراحی شده است. در ابتدای برنامه عبارت زیر ظاهر میشود:

```
CHOOSE ONE OF THIS OPTIONS:
 A - USE A DEFAULT DFA
 B - CREATE YOUR OWN DFA
 Q - EXIT THE PROGRAM
```
که واضح است هر کدام از قسمت ها مربوط به چه کاری هستند. اگر ما یکی از حالات داده شده را انتخاب کنیم بسته به مطالبی که در قسمت کلاس پذیرنده توضیح داده شد، ماشین مورد نظرمان را میسازیم. بعد از ساخته شدن این ماشین پیام زیر ظاهر میگردد: 
```
NOW YOU CAN ENTER STRINGS WITH THIS ALPHABETS:
[a, b]
ENTER " /quit " TO GO BACK.
ENTER " /status " TO GET THE DFA'S STATUS.
```
که وضعیت این پذیرنده بصورت زیر نشان داده میشود:
```
>>> ALPHABETS:
   >>> a
   >>> b
---------------------------------------
>>> ALL OF THE STATES:
   >>> Q0
   >>> Q1
   >>> Q2
---------------------------------------
>>> START STATES: 
   >>> Q0
---------------------------------------
>>> FINAL STATES:
   >>> Q1
---------------------------------------
>>> PATHS:
   >>> ( Q0 ) ----- a -----> ( Q1 )
   >>> ( Q0 ) ----- b -----> ( Q1 )
   >>> ( Q1 ) ----- a -----> ( Q2 )
   >>> ( Q1 ) ----- b -----> ( Q2 )
   >>> ( Q2 ) ----- a -----> ( Q2 )
   >>> ( Q2 ) ----- b -----> ( Q2 )
```
اگر هر چیز دیگری به غیر از دو دستور گفته شده وارد شود، برنامه آن را یک رشته ی ورودی در نظر میگیرد و آن را تحلیل میکند. کاراکترها را از چپ به راست بررسی میکند و مشخض میکند که این ورودی مقبولِ این ماشین میباشد یا خیر. بدیهی است که اگر یکی از ورودی ها نامعتبر باشد ماشین دست از کار میکشد و اعلام میکند که این ورودی نامعتبر بوده و منتظر ورودی های بعدی نمیماند. بعنوان مثال اگر ما ورودی زیر را به این پذیرنده بدهیم، ورودی های زیر را دریافت خواهیم کرد:
```
INPUT STRING: " abadi "
( 1 = SUCCESSFUL STEP , 0 = FAILED)
( Q0 ) ----- a -----> ( Q1 )

| a | b | a | d | i |
| 1 | - | - | - | - |

( Q1 ) ----- b -----> ( Q2 )

| a | b | a | d | i |
| 1 | 1 | - | - | - |

( Q2 ) ----- a -----> ( Q2 )

| a | b | a | d | i |
| 1 | 1 | 1 | - | - |

ERROR FOUND WHILE READING INPUT "d"!

| a | b | a | d | i |
| 1 | 1 | 1 | 0 | - |

THIS STRING IS NOT VALID!!!!
```
مشاهده میشود که در هر مرحله علاوه بر نمایش مسیر مورد نظر، زیر ورودی های کاربر عبارات زیر نمایش داده شده اند:

-	:1 یعنی این ورودی خاص توسط پذیرنده به درستی پذیرفته شده و مشکلی پیش نیامده است.

-	:0 یعنی با خطا روبرو شده ایم! خطا هم میتواند ملاحظه‌ی حرفی خارج از الفبای این ماشین و هم میتواند زمانی باشد که با الفبای مورد نظر مسیری به یک حالت دیگر وجود نداشته باشد.

-	-: یعنی هنوز زمان بررسی این الفبا فرا نرسیده است.

منطقی است زمانی که در مرحله ی آخر عبارت زیر رشته‌ی ورودی کاربر بصورت 111..1 باشد. یعنی مشکلی رخ نداده است. حال باید به حالتی که دستگاه در حال حاضر در آن قرار دارد نگاه کنیم. اگر این حالت جزو حالات نهایی این دستگاه بود یعنی این رشته پذیرفته شده، وگرنه رشته‌ی ورودی معتبر نخواهد بود!

