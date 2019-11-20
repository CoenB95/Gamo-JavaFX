@echo off
echo Linking...
jlink --module-path "target;%PATH_TO_FX_MODS%" --add-modules gamo --launcher gamo=gamo/com.cbapps.javafx.gamo.test.TestMain --output out
echo Done