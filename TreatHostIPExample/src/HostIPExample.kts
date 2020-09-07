import java.net.InetAddress

// この方法では、複数の IP アドレス（ループバック、 Wi-Fi 側、 Ethernet 側など）のうちどれが取れるかわからない
val oneOfAddresses: String = InetAddress.getLocalHost().hostAddress
println("IP アドレスのうちのひとつ: $oneOfAddresses")

// macOS 固有となるが、 networksetup コマンドを使うとインターフェイスを指定して IP アドレス取得可能
val wifiAddress = {
    val command = "networksetup -getinfo Wi-Fi"
    val process = Runtime.getRuntime().exec(command)
    process.waitFor()

    val result = process.inputStream
        .reader().readText()
        .substringAfter("IP address: ")
        .substringBefore("\n")
    process.destroy()
    result
}()
println("Wi-Fi インターフェイスの IP アドレス: $wifiAddress")

println("=============================================")

// 上記の substring する前の、コマンド出力結果
val commandOutput = {
    val command = "networksetup -getinfo Wi-Fi"
    val process = Runtime.getRuntime().exec(command)
    process.waitFor()

    val result = process.inputStream
        .reader().readText()
    process.destroy()
    result
}()
println("""networksetup -getinfo Wi-Fi コマンドの出力結果:
$commandOutput
""")
