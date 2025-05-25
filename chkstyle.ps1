Get-ChildItem -Recurse -Include *.java | Where-Object {
    $_.FullName -notmatch "Keyboard\.java" -and
    $_.FullName -notmatch "test" -and
    $_.FullName -notmatch "out" -and
    $_.FullName -notmatch "target" -and
    $_.FullName -notmatch "build"
} | ForEach-Object {
    java -jar "checkstyle.jar" -c "checkstyle.xml" $_.FullName
}
