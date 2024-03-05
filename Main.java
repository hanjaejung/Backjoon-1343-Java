package com.example.javastudy.codingStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        Stack stc = new Stack();
        StringBuilder sb = new StringBuilder(); //최종값을 넣어주기 위한 스트링빌더

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '.'){ //점일시 스택에 값이 존재시에만 스택의 길이만큼 검사하여 A와 B 스트링빌더에 쌓기
                if(stc.size() % 2 != 0){ //홀수면은 검사할 필요없이 break 반복문 빠져 나온 후 스택에 값이 남아있으면 맨밑에서 스택값을 뽑는다
                    break;
                }
                if(!stc.isEmpty()){
                    if(stc.size() % 4 == 0){ //4의 배수는 무조건 A
                        while(!stc.isEmpty()){

                            stc.pop();
                            sb.append("A");

                        }
                    }else{ //4의 배수가 아니면 A와 B가 섞여있으니 아래 메소드 실행
                        sb.append(twoAndFourSum(stc));
                    }
                }
                sb.append(str.charAt(i)); //스택에 쌓여있는 길이를 계산 후 점찍기
            }else{
                stc.push(str.charAt(i)); //점이외의 문자를 스택에 쌓기
            }
        }

        if(stc.size() % 2 == 0){ //문자열 전체 검사 후 마지막 문자가 점이 아니면 스택에 남아있는 값을 추출해야 하므로 한번 더 추출작업

            if(stc.size() % 4 == 0){ //4의 배수면 다 A이다
                while(!stc.isEmpty()){

                    stc.pop();
                    sb.append("A");

                }
            }else{
                sb.append(twoAndFourSum(stc)); //4의 배수가 아니면 A와 B가 섞여있으니 아래 메소드 실행
            }
            System.out.println(sb); //지금까지 쌓여있는 스트링빌더 출력
        }else{ //홀수일시 -1 출력
            System.out.println(-1);
        }
    }

    static String twoAndFourSum(Stack stack){
        StringBuilder mr = new StringBuilder();
        int counts = 0;
        while(!stack.isEmpty()){ // 여기 부터는 짝수이나 4의 배수가 아니니 A와 B가 섞여있다 A는 4의 배수로 찾아내고 B는 2로 찾아낸다
            if(counts < 2){ //B는 2개 이니 처음 2개까지만 스택 삭제 후 B쌓기

                stack.pop();
                mr.append("B");
                counts++;

            }else{ //counts가 2 이상이니 B검사를 한것이므로 4의 배수 및 또 숨어있는 2검사
                if(stack.size() % 4 == 0){ //4의 배수인지 검사
                    while(!stack.isEmpty()){ //4의 배수가 맞으면 남은 스택을 다돌려서 스택 하나씩 다 제거하면서 A추가

                        stack.pop();
                        mr.append("A");
                    }
                }else{ // 4의 배수가 아니면 다시 2씩 B로 채우기 위해 counts 0으로 초기화
                    counts = 0;
                }
            }

        }//XXXXXX 이면 BBAAAA 로 나오니 reverse메소드를 써서 뒤집어 준다
        return mr.reverse().toString();

    }
}
