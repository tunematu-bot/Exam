package tool;

// ↑ tool パッケージに属するクラス
 
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest; // ★アクセス情報を取るために追加

// ↑ フィルタ機能を使うためのクラス・インタフェースを読み込む
 
@WebFilter(urlPatterns = { "/*" })

// ↑ このフィルタを「すべてのURL」に適用する設定

//   /* は「全リクエスト」を意味する

public class EncodingFilter implements Filter {

    // ↑ Filter インタフェースを実装したクラス

    //   → フィルタとして動作する
 
    /**

     * doFilterメソッド

     * サーブレットやJSPが実行される前後に動く処理を書く

     */

    public void doFilter(ServletRequest request,

                         ServletResponse response,

                         FilterChain chain)

            throws IOException, ServletException {
 
        // --- ここから追記 ---

        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println("=== 処理開始: " + req.getRequestURI() + " ===");

        long start = System.currentTimeMillis(); // 実行時間の計測開始

        // ------------------
 
        // リクエストの文字コードを UTF-8 に設定

        // フォーム入力などの日本語文字化け防止

        request.setCharacterEncoding("UTF-8");
 
        // レスポンスの文字コードを UTF-8 に設定

        // ブラウザに UTF-8 の HTML として返す

        response.setContentType("text/html; charset=UTF-8");
 
        // フィルタの「前処理」

        // System.out.println("フィルタの前処理");
 
        // 次의 処理（別のフィルタ or Servlet / Action）を実行

        chain.doFilter(request, response);
 
        // フィルタの「後処理」

        // System.out.println("フィルタの後処理");
 
        // --- ここから追記 ---

        long end = System.currentTimeMillis(); // 実行時間の計測終了

        System.out.println("=== 処理終了 (時間: " + (end - start) + "ms) ===");

        System.out.println(""); // コンソールを見やすくするための空行

        // ------------------

    }
 
    // フィルタが最初に読み込まれたときに1回だけ呼ばれる

    public void init(FilterConfig filterConfig) {

        // ★ 起動確認用に追記

        System.out.println("EncodingFilter が初期化されました。");

    }
 
    // フィルタが破棄されるときに呼ばれる

    public void destroy() {

        // ★ 終了確認用に追記

        System.out.println("EncodingFilter が破棄されました。");

    }

}

