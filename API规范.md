# URI规范
* 不使用大写
* 使用短横线`-`连接单词而不是下划线`_`
* URI表示资源，因此不要使用动词，要使用名词的复数形式
* 例子
	资源集合：
    - `http://api.user.com/schools/grades/classes/boys`
    学校所有男生
    - `http://api.college.com/students/123/courses`
    学号为123的学生的课程
	单个资源：
	- `http://api.college.com/students/123`
	学号为123的学生
	- `http://api.college.com/students/1;2;3`
	学号为1、2、3的学生
* 正斜杠分隔符`/`用于指示层级关系
* 避免层级过深的URI，一般不超过3级
  如果层级过深，尽量使用参数QueryParam代替路径中的实体导航，如`http://api.college.com/students?id=123&class=3&sort=age,desc&limit=10`
* 对于一些特殊的功能请求，不涉及具体资源的，允许在URI当中使用动词，如用户登录 `http://api.college.com/login`

# QueryParam参数规范
| 功能 | 用法 |
| -------- | -------- |
| 过滤条件 | ?type=1&age=16 |
| 排序 | ?sort=age,desc |
| 投影 | ?whitelist=id,name,email |
| 分页(页码方式) | ?page=3&pagesize=10 |
| 分页(游标方式) | ?cursor=20&limit=10 |
| 搜索 | ?k=关键字 |
| 指定数量 |  ?limit=10 |
| 指定位置 |  ?offset=10 |
| 数组类型 | ?courses=math,chinese,english |

# QueryParam分页规范
* 页码方式
  - page: 页码
  - pagesize: 每页条目数
* 游标方式
  - cursor: 游标
  - limit: 下一页条目数
 
# HTTP动词规范
* HTTP请求方式代表对资源的具体操作，由动词表示

| 请求方式 | 描述 |
| -------- | -------- |
| GET | 从服务器取出资源(一项或多项) |
| POST | 在服务器新建一个资源 |
| PUT | 在服务器更新资源(客户端提供改变后的完整资源) |
| PATCH | 在服务器更新资源(客户端仅提供改变的属性) |
| DELETE | 从服务器删除资源(一项或多项) |
| OPTIONS | 获取信息，关于资源的哪些属性是客户端可以改变的，不常用 |
| HEAD |  获取资源的元数据，不常用 |

# HTTP状态码规范
| 常用状态码 | 描述 |
| -------- | -------- |
| 200   | 服务器已成功处理了请求   |
| 201   | 请求成功并且服务器已创建了新资源   |
| 202   | 服务器已接受请求，但尚未处理（异步）   |
| 204   | 服务器成功处理了请求，但没有返回任何主体内容   |
| 400   | 错误请求，服务器不理解请求的语法   |
| 401   | 未授权，请求要求身份验证   |
| 403   | 服务器拒绝请求   |
| 404   | 服务器找不到请求的网页   |
| 500   | 服务器遇到错误，无法完成请求   |

# 自定义状态码规范
| 自定义状态码 | 描述 |
| -------- | -------- |
| 0 | 操作成功 |
| 1 | 服务器内部错误，错误码统一返回1，不暴露内部错误信息，内部错误信息通过日志记录 |
| 2XXX | 客户端请求的相关错误，例如：缺少必要参数、非法参数、权限不足、账号密码错误、名称已存在等 |

# 时间格式规范
* 时间格式统一使用 ISO_INSTANT
* 精确到毫秒表示为： 2018-05-03T16:06:49.117Z
* 精确到秒表示为： 2018-05-04T00:06:49Z

# 请求体格式规范
* 针对POST、PUT、PATCH请求方式的请求体requestbody统一采用 application/json 方式
* 例子
```HTTP
 {
	"id": 1,
	"name": "zst",
	"age": 18,
	"classId": 6
 }
```

```HTTP
{
	"id": 1,
	"name": "zst",
	"age": 18,
	"class": {
		"classId": 6,
		"grade": 1,
		"studentNumber": 50
	}
}
```

```HTTP
{
	"id": 1,
	"name": "zst",
	"age": 18,
	"class": {
		"classId": 6,
		"grade": 1,
		"studentNumber": 50
	},
	"course": [{
		"courseId": 1,
		"courseName": "math",
		"teacherId": 1
	}, {
		"courseId": 2,
		"courseName": "chinese",
		"teacherId": 2
	}]
}
```

```HTTP
[{
		"id": 1,
		"name": "zst",
		"age": 18,
		"class": {
			"classId": 6,
			"grade": 1,
			"studentNumber": 50
		},
		"course": [{
			"courseId": 1,
			"courseName": "math",
			"teacherId": 1
		}, {
			"courseId": 2,
			"courseName": "chinese",
			"teacherId": 2
		}]
	},
	{
		"id": 2,
		"name": "abc",
		"age": 19,
		"class": {
			"classId": 5,
			"grade": 2,
			"studentNumber": 52
		},
		"course": [{
			"courseId": 1,
			"courseName": "math",
			"teacherId": 5
		}, {
			"courseId": 2,
			"courseName": "chinese",
			"teacherId": 2
		}]
	}
]
```


# 返回值格式规范
* 操作成功
```HTTP
HTTP/1.1 200 OK
{
    "errorCode": 0,
    "errorInfo": "success"
}
```

* 操作成功(带Object)
```HTTP
HTTP/1.1 200 OK
{
    "errorCode": 0,
    "errorInfo": "success",
    "data": {
        "foo": "bar",
        "foo1": "bar1"
    }
}
```

* 操作成功(带Object，Object带datetime)
```HTTP
HTTP/1.1 200 OK
{
    "errorCode": 0,
    "errorInfo": "success",
    "data": {
        "foo": "bar",
        "foo1": "bar1",
        "datetime": "2018-05-04T00:06:49Z"
    }
}
```


* 操作成功(带Array)
```HTTP
HTTP/1.1 200 OK
{
    "errorCode": 0,
    "errorInfo": "success",
    "data": [
        {
            "foo": "bar",
            "foo1": "bar1"
        },
        {
            "foo": "bar",
            "foo1": "bar1"
        }
    ]
}
```

* 操作成功(带Array和cursor)
```HTTP
HTTP/1.1 200 OK
{
    "errorCode": 0,
    "errorInfo": "success",
    "cursor": "xxxx",
    "data": [
        {
            "foo": "bar",
            "foo1": "bar1"
        },
        {
            "foo": "bar",
            "foo1": "bar1"
        }
    ]
}
```

* 服务器内部错误
```HTTP
HTTP/1.1 500 Internal Server Error
{
    "errorCode": 1,
    "errorInfo": "Internal Server Error"
}
```

* 名称已存在
```HTTP
HTTP/1.1 400 Bad Request
{
    "errorCode": 2001,
    "errorInfo": "name exists"
}
```

* 缺少必要参数
```HTTP
HTTP/1.1 400 Bad Request
{
    "errorCode": 2004,
    "errorInfo": "lack necessary parameter"
}
```

* 非法参数
```HTTP
HTTP/1.1 400 Bad Request
{
    "errorCode": 2005,
    "errorInfo": "invalid parameter"
}
```

* 时间解析错误
```HTTP
HTTP/1.1 400 Bad Request
{
    "errorCode": 2008,
    "errorInfo": "datetime parse error"
}
```

* 用户名或密码错误
```HTTP
HTTP/1.1 401 Unauthorized
{
    "errorCode": 2003,
    "errorInfo": "username or password is error"
}
```

* 无操作权限
```HTTP
HTTP/1.1 401 Unauthorized
{
    "errorCode": 2006,
    "errorInfo": "no operation permission"
}
```

* 请求资源不存在
```HTTP
HTTP/1.1 404 Not Found
{
    "errorCode": 2007,
    "errorInfo": "no such request is existed"
}
```


# 服务端代码规范
* 请根据实际需求，在枚举类ApiStatus中添加更多的自定义状态码errorCode，和对应的状态信息errorInfo
* 返回值封装在ApiResponse类中，其中errorCode和errorInfo为必填项，cursor和data为选填项
* 当返回值没有data和cursor时，调用ApiResponse(ApiStatus status)构造函数，用于POST、PATCH、PUT、DELETE请求返回以及操作失败的情况
* 当返回值有data时，调用ApiResponse(Object data)构造函数，当有data和cursor分页时，调用ApiResponse(Object data, String cursor)构造函数，用于GET请求返回
* 当产生异常时，首先使用ApiExceptionHandler类捕获异常，其中
  - 服务器内部异常如IOException、RunTimeException，捕获后打印错误日志，并返回统一的错误状态码1，错误状态信息"Internal Server Error"到客户端；
  - 客户端请求发生错误时，抛出UserException异常，捕获后不打印错误日志，调用ApiResponse(UserException e)构造函数返回客户端

* 具体实现，详见SRE下的api-demo项目