#version 120

// Phong shading fragment shader
uniform vec4 lightcolor;
uniform vec4 ambient;
uniform vec4 diffuse;
uniform vec4 specular;

uniform float ambientCoefficient;
uniform float diffuseCoefficient;
uniform float specularCoefficient;

uniform float specularExponent;

varying vec3 lPos;
varying vec3 vPos;
varying vec3 vNorm;

varying vec2 texCoord;
uniform sampler2D myTexture;

void main()
{
    vec3 L = normalize (lPos - vPos);
    vec3 N = normalize (vNorm);
    
    vec3 R = (-2 * N * dot (L,N)) + L;
    
    
     // calculate components
    vec4 diffuseC = lightcolor * diffuse * (dot(N, L));
    vec4 ambientC = lightcolor * ambient * (dot(N, L));
	float spec = dot (R, normalize(vPos));
	if (spec < 0){
		spec = 0;
	}
	spec = pow(spec, specularExponent);
	vec4 specularC = spec * lightcolor * specular;

    // set the final color
    gl_FragColor = texture2D(myTexture, texCoord) * (diffuseC);

}
