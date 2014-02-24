echo -n "Do you agree? [y|n] "
read n
echo "Answer: $n"
case "$n" in
    y|Y)
        echo "You said yes."
        ;;
    n|N)
        echo "You said no."
        ;;
    *)
        echo "You're a fool, Harry Potter, and you will lose."
        ;;
esac
