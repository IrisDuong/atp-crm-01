import React from 'react'
import { Button } from 'antd';
import { GATEWAY_URI } from '../../../../util/app.config';
function GoogleLogin() {
  return (
    <div>
        <Button type="primary" shape="round" size='large'
        
        onClick={() => { window.location.href= `${GATEWAY_URI}/auth-mgt/oauth2/authorization/google` }}
        >
            Login with Google
        </Button>
    </div>
  )
}

export default GoogleLogin