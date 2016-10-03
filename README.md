# How to create html output after compilation?

    dot -Tsvg astAutoProj.dot > astAutoProj.svg; cat svg-html-template-before.html astAutoProj.svg svg-html-template-after.html > index-gen.html; open index-gen.html

