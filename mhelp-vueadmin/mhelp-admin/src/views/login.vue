<template>
  <el-row type="flex" class="row-bg" justify="center">
    <el-col :lg="9" :xl="11">
      <div>
        <h2>多帮管理云端</h2>
        <el-image style="width: 100px;height: 100px" :src="require('@/assets/logo.png')"></el-image>
        <p>公众号 神的孩子都在歌唱</p>
        <p>扫码关注</p>
      </div>
    </el-col>
    <el-col span="1">
      <el-divider direction="vertical"></el-divider>
    </el-col>
    <el-col :lg="9" :xl="11">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="code">
          <el-input class="el-input" v-model.number="ruleForm.code" style="width: 150px;float: left"></el-input>
          <el-image class="captchatImg" :src="captchaImg" @click="getCaptcha"></el-image>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>

        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "login",
  data() {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('验证码不能为空'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          if (value < 18) {
            callback(new Error('必须是四位数字哦'));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        //验证码
        code: '',
        token: ' '
      },
      rules: {
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ],
        code: [
          {validator: checkAge, trigger: 'blur'}
        ]
      },
      //验证码图片
      captchaImg: ''
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //获取验证码
    getCaptcha() {
      this.$axios.get('http://101.35.145.209:8889/captcha').then(res => {
        console.log(res.data);
        this.ruleForm.token=res.data.data.token;
        this.captchaImg=res.data.data.captchaImg

      })
    },
  },
  created() {
    this.getCaptcha()
  }
}
</script>
<!--https://www.runoob.com/cssref/css3-pr-justify-content.html-->
<style scoped>
.el-col {
  /*弹性布局*/
  display: flex;
  /* 居中排列 */
  justify-content: center;
  height: 100vh;
  /*垂直排列*/
  align-items: center;
  /*文本居中对齐*/
  text-align: center;
}

.el-row {
  height: 100%;
  background-color: #fafafa;
}

.el-divider {
  height: 200px;
}

/*验证码样式*/
.captchatImg {
  float: left;
  /*设置元素的左边距*/
  margin-left: 8px;
  /*添加圆角的边框*/
  width:90px;
  border-radius: 4px;
}

</style>
